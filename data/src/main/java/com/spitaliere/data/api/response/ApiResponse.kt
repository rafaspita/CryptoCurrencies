package com.spitaliere.data.api.response

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class ApiResponse<ENTITY>(
    var data: List<ENTITY>,
    var timestamp: Long
)