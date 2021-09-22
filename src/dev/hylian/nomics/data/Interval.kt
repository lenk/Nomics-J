package dev.hylian.nomics.data

enum class Interval(val code: String) {
    ONE_DAY("1d"),
    ONE_HOUR("1h"),
    WEEK("7d"),
    MONTH("30d"),
    YEAR("365d"),
    YTD("ytd")
}