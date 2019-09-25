package com.spitaliere.domain.features.selection.repository

import androidx.paging.DataSource
import com.spitaliere.domain.features.selection.entity.Selection
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface SelectionRepository {

    fun update(list: List<Selection>) : Completable

    fun getAll() : Single<List<Selection>>

    fun syncCurrenciesForSelection() : Single<Boolean>

    fun getAllSelected(name: String): DataSource.Factory<Int, Selection>
}