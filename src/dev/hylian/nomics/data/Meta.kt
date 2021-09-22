package dev.hylian.nomics.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

class Meta : ListResult<Meta.Data>() {

    open class Data {
        var replaced_by : String ? = null
        var cryptocontrol_coin_id : String ? = null
        var original_symbol : String ? = null
        var block_explorer_url : String ? = null
        var bitcointalk_url : String ? = null
        var whitepaper_url : String ? = null
        var telegram_url : String ? = null
        var facebook_url : String ? = null
        var website_url : String ? = null
        var youtube_url : String ? = null
        var github_url : String ? = null
        var medium_url : String ? = null
        var reddit_url : String ? = null
        var description : String ? = null
        var twitter_url : String ? = null
        var linkedin_url : String ? = null
        var discord_url : String ? = null
        var logo_url : String ? = null
        var blog_url : String ? = null
        var name : String ? = null
        var id : String ? = null

        var used_for_pricing : Boolean = false
    }
}