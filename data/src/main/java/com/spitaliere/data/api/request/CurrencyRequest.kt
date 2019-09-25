package com.spitaliere.data.api.request

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class CurrencyRequest(
    var search: String = "",
    var ids: String = "",
    var limit: String = "2000"
)
