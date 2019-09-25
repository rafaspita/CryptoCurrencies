package com.spitaliere.data.features.currency.datasource.remote

import com.spitaliere.data.api.request.CurrencyRequest
import com.spitaliere.data.features.currency.entity.CurrencyCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface CurrencyRemoteDataSource {

    fun fetchCurrencies(currencyRequest: CurrencyRequest) : Single<List<CurrencyCache>>
}