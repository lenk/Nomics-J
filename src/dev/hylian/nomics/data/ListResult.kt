package dev.hylian.nomics.data

import com.fasterxml.jackson.databind.ObjectMapper

open class ListResult<type> : ArrayList<type>() {

    private val mapper = ObjectMapper()

    override fun toString(): String {
        return mapper.writeValueAsString(this)
    }

}