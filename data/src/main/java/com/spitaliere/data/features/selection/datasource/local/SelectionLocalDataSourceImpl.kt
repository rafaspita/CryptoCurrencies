package com.spitaliere.data.features.selection.datasource.local

import androidx.paging.DataSource
import com.spitaliere.data.features.selection.dao.SelectionDao
import com.spitaliere.data.features.selection.entity.SelectionCache
import com.spitaliere.data.features.selection.entity.mapToSelectionDomain
import com.spitaliere.domain.features.selection.entity.Selection
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class SelectionLocalDataSourceImpl(private val selectionDao: SelectionDao) : SelectionLocalDataSource {

    override fun update(list: List<SelectionCache>): Completable = selectionDao.update(list)

    override fun getAllSelections(name: String): DataSource.Factory<Int, SelectionCache> = if (name.isBlank()) selectionDao.getAllSelections() else selectionDao.getAllSelectionsByName(name)

    override fun save(list: List<SelectionCache>) : Completable = selectionDao.save(list)

    override fun getAll(): Single<List<Selection>> = selectionDao.getAll().map { it.mapToSelectionDomain() }

    override fun getSelected(): Single<List<String>> = selectionDao.getSelected()
}