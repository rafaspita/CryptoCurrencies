package com.spitaliere.data.features.currency.datasource.local

import com.spitaliere.data.features.currency.dao.CurrencyDao
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.data.features.currency.entity.mapToDomain
import com.spitaliere.domain.features.currency.entity.Currency
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class CurrencyLocalDataSourceImpl(private val currencyDao: CurrencyDao) : CurrencyLocalDataSource {

    override fun getAll(): Flowable<List<Currency>> = currencyDao.getCurrencies().map { it.mapToDomain() }

    override fun saveCache(currencies: List<CurrencyCache>) : Completable = currencyDao.save(currencies)
}