package com.spitaliere.data.features.selection.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.domain.features.selection.entity.Selection

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Entity(tableName = "selection")
data class SelectionCache(
    @PrimaryKey
    var id: String = "",
    var coin: String = "",
    var coinName:String = "",
    var selected: Boolean = false,
    var sort: Int = 0
)

fun List<CurrencyCache>.mapToDomain() : List<Selection> = map { it.mapToSelection() }

fun CurrencyCache.mapToSelection() : Selection = Selection(
    id = id,
    coin = symbol.toLowerCase(),
    coinName = name
)

fun List<CurrencyCache>.mapToSaveData() : List<SelectionCache> = map { it.mapToSelectionCache() }

fun CurrencyCache.mapToSelectionCache() : SelectionCache = SelectionCache(
    id = id,
    coin = symbol.toLowerCase(),
    coinName = name,
    sort = rank
)

fun List<SelectionCache>.mapToSelectionDomain() : List<Selection> = map { it.mapToDomain() }

fun SelectionCache.mapToDomain() : Selection = Selection(
    id = id,
    coin = coin,
    coinName = coinName,
    selected = selected
)

fun List<Selection>.mapToData() : List<SelectionCache> = map { it.mapToData() }

fun Selection.mapToData() : SelectionCache = SelectionCache(
    id = id,
    coin = coin,
    coinName = coinName,
    selected = selected
)