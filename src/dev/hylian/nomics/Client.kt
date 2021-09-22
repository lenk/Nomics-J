package dev.hylian.nomics

import com.fasterxml.jackson.databind.ObjectMapper
import dev.hylian.nomics.data.*
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.utils.URIBuilder
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.jsoup.HttpStatusException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Client(private val key: String) {
    private val mapper = ObjectMapper()

    private val utcFormat = SimpleDateFormat("yyyy-MM-dd\'T\'hh:mm:ss\'Z\'")
    private val http: CloseableHttpClient = HttpClients.createDefault()
    private val currencies = "https://api.nomics.com/v1/currencies/"
    private val market = "https://api.nomics.com/v1/market-cap"
    private val volume = "https://api.nomics.com/v1/volume"


    /**
     * Volume History is the total volume for all crypto-assets in [toCurrency] at intervals
     * between the requested [start] and [end] time period.
     *
     * For each entry, the volume field represents the sum of the spot_volume and derivative_volume fields.
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getVolume(start: Date, end: Date ? = null, toCurrency: String = "USD"): Volume {
        synchronized(http) {
            val request = HttpGet("$volume/history")

            ((request as HttpRequestBase).uri) = URIBuilder(request.uri)
                .addParameter("end", if (end != null) utcFormat.format(end) else "")
                .addParameter("start", utcFormat.format(start))
                .addParameter("convert", toCurrency)
                .addParameter("key", key).build()

            return mapper.readValue(((http.execute(request).entity.content)), Volume::class.java)
        }
    }


    /**
     * MarketCap History is the total market cap in [toCurrency] for all crypto-assets at intervals between
     * the requested [start] and [end] time period.
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getMarket(start: Date, end: Date ? = null, toCurrency: String = "USD"): Market {
        synchronized(http) {
            val request = HttpGet("$market/history")

            ((request as HttpRequestBase).uri) = URIBuilder(request.uri)
                .addParameter("end", if (end != null) utcFormat.format(end) else "")
                .addParameter("start", utcFormat.format(start))
                .addParameter("convert", toCurrency)
                .addParameter("key", key).build()

            return mapper.readValue(((http.execute(request).entity.content)), Market::class.java)
        }
    }


    /**
     * The currencies endpoint returns all the [shortCurrency] and their metadata that Nomics supports
     * from the [start] to the [end] date if provided
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getSparkline(shortCurrency: String, start: Date, end: Date ? = null, toCurrency: String = "USD"): Sparkline {
        return getSparkline(arrayOf(shortCurrency), start, end, toCurrency)
    }

    /**
     * The currencies endpoint returns all the [shortCurrency] and their metadata that Nomics supports
     * from the [start] to the [end] date if provided
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getSparkline(shortCurrency: Array<String>, start: Date, end: Date ? = null, toCurrency: String = "USD"): Sparkline {
        synchronized(http) {
            val request = HttpGet("$currencies/sparkline")

            ((request as HttpRequestBase).uri) = URIBuilder(request.uri)
                .addParameter("ids", shortCurrency.joinToString(","))
                .addParameter("end", if (end != null) utcFormat.format(end) else "")
                .addParameter("start", utcFormat.format(start))
                .addParameter("convert", toCurrency)
                .addParameter("key", key).build()

            return mapper.readValue(((http.execute(request).entity.content)), Sparkline::class.java)
        }
    }

    /**
     * The currencies endpoint returns all the [shortCurrency] and their metadata that Nomics supports.
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getMeta(shortCurrency: String): Meta {
        return getMeta(arrayOf(shortCurrency))
    }

    /**
     * The currencies endpoint returns all the [shortCurrency] and their metadata that Nomics supports.
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getMeta(shortCurrency: Array<String>): Meta {

        synchronized(http) {
            val request = HttpGet(currencies)

            ((request as HttpRequestBase).uri) = URIBuilder(request.uri)
                .addParameter("ids", shortCurrency.joinToString(","))
                .addParameter("key", key).build()


            return mapper.readValue(((http.execute(request).entity.content)), Meta::class.java)
        }
    }

    /**
     * [sort] Price, volume, market cap, and rank [shortCurrency] across [intervals]
     * converting the price value [toCurrency]
     *
     * Current prices are updated every 10 seconds
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getTicker(shortCurrency: String, toCurrency: String = "USD", intervals: Array<Interval> = arrayOf(Interval.YTD),
                  sort: Sort = Sort.RANK, perPage: Int = 100, page: Int = 1, status: Status = Status.active): Ticker {

        return getTicker(arrayOf(shortCurrency), toCurrency, intervals, sort, perPage, page, status)
    }

    /**
     * [sort] Price, volume, market cap, and rank [shortCurrency]'s across [intervals]
     * converting the price value [toCurrency]
     *
     * Current prices are updated every 10 seconds
     */

    @Throws(HttpStatusException::class, IOException::class)
    fun getTicker(shortCurrency: Array<String>, toCurrency: String = "USD", intervals: Array<Interval> = arrayOf(Interval.YTD),
                  sort: Sort = Sort.RANK, perPage: Int = 100, page: Int = 1, status: Status = Status.active): Ticker {

        synchronized(http) {
            val request = HttpGet("$currencies/ticker")

            ((request as HttpRequestBase).uri) = URIBuilder(request.uri)
                .addParameter("interval", intervals.joinToString(",") { interval -> interval.code })
                .addParameter("ids", shortCurrency.joinToString(","))
                .addParameter("per-page", perPage.toString())
                .addParameter("page", page.toString())
                .addParameter("convert", toCurrency)
                .addParameter("status", status.code)
                .addParameter("sort", sort.code)
                .addParameter("key", key).build()


            return mapper.readValue(((http.execute(request).entity.content)), Ticker::class.java)
        }
    }

    fun close() {
        http.close()
    }
}