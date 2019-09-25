package com.spitaliere.data.features.currency.repository

import com.spitaliere.data.api.request.CurrencyRequest
import com.spitaliere.data.features.currency.datasource.local.CurrencyLocalDataSource
import com.spitaliere.data.features.currency.datasource.remote.CurrencyRemoteDataSource
import com.spitaliere.data.features.selection.datasource.local.SelectionLocalDataSource
import com.spitaliere.data.preferences.Preferences
import com.spitaliere.data.preferences.PreferencesImpl
import com.spitaliere.domain.features.currency.entity.Currency
import com.spitaliere.domain.features.currency.repository.CurrencyRepository
import com.spitaliere.domain.platform.exception.TimeException
import com.spitaliere.domain.platform.extension.passedAMinute
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.*

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class CurrencyRepositoryImpl(
    private val remoteDataSource: CurrencyRemoteDataSource,
    private val localDataSource: CurrencyLocalDataSource,
    private val selectionLocal : SelectionLocalDataSource,
    private val preferences: Preferences
): CurrencyRepository {

    override fun updateCurrencies(force: Boolean): Completable {
        return selectionLocal.getSelected()
            .filter { canUpdate(force) }
            .flatMapCompletable { it ->
                remoteDataSource.fetchCurrencies(CurrencyRequest(limit = it.size.toString(),ids = it.joinToString (separator = ",")))
                    .flatMapCompletable{ localDataSource.saveCache(it) }
            }
    }

    override fun getAllCurrencies(): Flowable<List<Currency>> = localDataSource.getAll()

    private fun canUpdate(force: Boolean): Boolean {
        val canUpdate = Calendar.getInstance().passedAMinute(preferences.getLong(PreferencesImpl.LAST_SYNC, 0)) || force
        if (!canUpdate) throw TimeException()
        return canUpdate
    }
}