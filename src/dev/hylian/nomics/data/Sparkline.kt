package dev.hylian.nomics.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

class Sparkline : ListResult<Sparkline.Spark>() {

    open class Spark {
        var timestamps: LinkedList<String> ? = null
        var prices: LinkedList<String> ? = null
        var currency: String ? = null
    }
}