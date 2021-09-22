package dev.hylian.nomics.data

class RatesHistory : ListResult<RatesHistory.Rate>() {

    open class Rate {
        var timestamp: String ? = null
        var rate: String ? = null
    }
}