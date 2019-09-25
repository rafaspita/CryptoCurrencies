package com.spitaliere.data.features.market.datasource.remote

import com.spitaliere.data.api.CoinCapApi
import com.spitaliere.data.features.market.entity.MarketCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class MarketRemoteDataSourceImpl(private val coinCapApi: CoinCapApi) : MarketRemoteDataSource {

    override fun getCoinMarkets(coin: String): Single<MarketCache> = coinCapApi.fetchMarkets(coin, coin).map { MarketCache(coinName = coin, marketCaches = it.data) }
}