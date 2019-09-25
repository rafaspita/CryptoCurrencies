package com.spitaliere.data.features.selection.repository

import androidx.paging.DataSource
import com.spitaliere.data.api.request.CurrencyRequest
import com.spitaliere.data.features.currency.datasource.remote.CurrencyRemoteDataSource
import com.spitaliere.data.features.selection.datasource.local.SelectionLocalDataSource
import com.spitaliere.data.features.selection.entity.mapToData
import com.spitaliere.data.features.selection.entity.mapToDomain
import com.spitaliere.data.features.selection.entity.mapToSaveData
import com.spitaliere.domain.features.selection.entity.Selection
import com.spitaliere.domain.features.selection.repository.SelectionRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class SelectionRepositoryImpl(
    private val localDataSource: SelectionLocalDataSource,
    private val currencyRemoteDataSource: CurrencyRemoteDataSource
): SelectionRepository {

    override fun update(list: List<Selection>): Completable = localDataSource.update(list.mapToData())

    override fun getAllSelected(name: String): DataSource.Factory<Int, Selection> = localDataSource.getAllSelections(name).map{ it.mapToDomain() }

    override fun syncCurrenciesForSelection(): Single<Boolean> = currencyRemoteDataSource.fetchCurrencies(CurrencyRequest())
        .flatMapCompletable { localDataSource.save(it.mapToSaveData())}
        .andThen(localDataSource.getSelected().map { it.isEmpty() })

    override fun getAll(): Single<List<Selection>> {
        return localDataSource.getAll()
            .flatMap {
                when {
                    it.isEmpty() -> getSelectionRemote()
                    else -> Single.just(it)
                }
            }
    }

    private fun getSelectionRemote(): Single<List<Selection>> =
        currencyRemoteDataSource.fetchCurrencies(CurrencyRequest()).map{ it.mapToDomain()}
}