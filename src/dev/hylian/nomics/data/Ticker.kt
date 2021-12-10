package dev.hylian.nomics.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

class Ticker : ListResult<Ticker.Result>() {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    open class Result {
        var platform_currency: String ? =null
        var rank_delta: String ? = null
        var logo_url: String ? = null
        var currency: String ? = null
        var symbol: String ? = null
        var rank: String ? = null
        var name: String ? = null
        var id: String ? = null

        var price_timestamp: String ? = null
        var price_date: String ? = null
        var status: Status ? = null
        var price: String ? = null

        var market_cap_dominance: String ? = null
        var num_pairs_unmapped: String ? = null
        var circulating_supply: String ? = null
        var num_exchanges: String ? = null
        var num_pairs: String ? = null
        var market_cap: String ? = null
        var max_supply: String ? = null

        var first_order_book: String ? = null
        var first_candle: String ? = null
        var first_trade: String ? = null

        var high_timestamp: String ? = null
        var high: String ? = null

        @JsonProperty("1d")
        var ONE_DAY : Change ? = null

        @JsonProperty("7d")
        var WEEK : Change ? = null

        @JsonProperty("30d")
        var MONTH : Change ? = null

        @JsonProperty("365d")
        var YEAR : Change ? = null

        @JsonProperty("ytd")
        var YTD : Change ? = null
    }
}