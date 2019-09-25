package com.spitaliere.data.features.currency.datasource.local

import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.domain.features.currency.entity.Currency
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface CurrencyLocalDataSource {

    fun getAll() : Flowable<List<Currency>>

    fun saveCache(currencies: List<CurrencyCache>) : Completable
}