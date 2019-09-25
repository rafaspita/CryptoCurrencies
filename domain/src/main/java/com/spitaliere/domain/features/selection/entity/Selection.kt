package com.spitaliere.domain.features.selection.entity

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class Selection(
    var id: String = "",
    var coin: String = "",
    var coinName:String = "",
    var selected: Boolean = false
)