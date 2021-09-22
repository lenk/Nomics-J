package dev.hylian.nomics.data

class Volume : ListResult<Volume.History>() {

    open class History {
        var timestamp: String ? = null
        var volume: String ? = null

        var transparent_derivative_volume: String ? = null
        var transparent_spot_volume: String ? = null
        var transparent_volume: String ? = null
        var derivative_volume: String ? = null
        var spot_volume: String ? = null
    }
}