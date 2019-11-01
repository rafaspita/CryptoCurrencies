package com.spitaliere.cryptocurrencies.ui.list.dialog

import android.content.Context

/**
 * Created by Rafael Spitaliere on 14/10/2019.
 **/
interface SortDialog {

    fun build(context: Context, sortByEnum: SortBy?, action: (SortBy) -> (Unit)) : SortDialog

    fun show()

    fun close()

    enum class SortBy {
        RANK, NAME, PRICE, CHANGE
    }
}