package com.spitaliere.data.features.currency.datasource.remote

import com.spitaliere.data.api.CoinCapApi
import com.spitaliere.data.api.request.CurrencyRequest
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.data.preferences.Preferences
import com.spitaliere.data.preferences.PreferencesImpl
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class CurrencyRemoteDataSourceImpl(
    private val coinCapApi: CoinCapApi,
    private val preferences: Preferences
) : CurrencyRemoteDataSource {

    override fun fetchCurrencies(currencyRequest: CurrencyRequest): Single<List<CurrencyCache>> {
        return coinCapApi.fetchCurrency(currencyRequest.limit, currencyRequest.ids)
            .map { apiResponse ->
                preferences.saveLong(PreferencesImpl.LAST_SYNC, apiResponse.timestamp)
                apiResponse.data.map { it.lastUpdate = apiResponse.timestamp }

                return@map apiResponse.data
            }
    }
}