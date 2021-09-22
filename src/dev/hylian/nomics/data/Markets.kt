package dev.hylian.nomics.data

class Markets : ListResult<Markets.Market>() {

    open class Market {
        var exchange: String ? = null
        var market: String ? = null
        var quote: String ? = null
        var base: String ? = null
    }
}