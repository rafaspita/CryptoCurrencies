package com.spitaliere.data.features.market.datasource.local

import com.spitaliere.data.features.market.entity.MarketCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface MarketLocalDataSource {

    fun save(marketCache: MarketCache) : Completable

    fun getAllFrom(coin: String): Flowable<MarketCache>
}