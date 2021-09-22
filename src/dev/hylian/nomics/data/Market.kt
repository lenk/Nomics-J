package dev.hylian.nomics.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

class Market : ListResult<Market.History>() {

    open class History {
        var market_cap : String ? = null
        var timestamp : String ? = null
    }
}