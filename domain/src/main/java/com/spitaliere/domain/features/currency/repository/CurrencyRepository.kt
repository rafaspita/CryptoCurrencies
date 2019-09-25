package com.spitaliere.domain.features.currency.repository

import com.spitaliere.domain.features.currency.entity.Currency
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface CurrencyRepository {

    fun updateCurrencies(force: Boolean): Completable

    fun getAllCurrencies(): Flowable<List<Currency>>
}