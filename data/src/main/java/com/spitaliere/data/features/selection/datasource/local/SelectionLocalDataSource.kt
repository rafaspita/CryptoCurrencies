package com.spitaliere.data.features.selection.datasource.local

import androidx.paging.DataSource
import com.spitaliere.data.features.selection.entity.SelectionCache
import com.spitaliere.domain.features.selection.entity.Selection
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface SelectionLocalDataSource {

    fun save(list :List<SelectionCache>) : Completable

    fun update(list :List<SelectionCache>) : Completable

    fun getAll() : Single<List<Selection>>

    fun getSelected() : Single<List<String>>

    fun getAllSelections(name: String): DataSource.Factory<Int, SelectionCache>
}