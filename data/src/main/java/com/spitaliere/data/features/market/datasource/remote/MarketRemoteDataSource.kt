package com.spitaliere.data.features.market.datasource.remote

import com.spitaliere.data.features.market.entity.MarketCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface MarketRemoteDataSource {

    fun getCoinMarkets(coin: String): Single<MarketCache>
}