package dev.hylian.nomics.data

class Rates : ListResult<Rates.Rate>() {

    open class Rate {
        var timestamp: String ? = null
        var currency: String ? = null
        var rate: String ? = null

    }
}