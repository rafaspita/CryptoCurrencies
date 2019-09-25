package com.spitaliere.data.features.market.datasource.local

import com.spitaliere.data.features.market.dao.MarketDao
import com.spitaliere.data.features.market.entity.MarketCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class MarketLocalDataSourceImpl(private val marketDao: MarketDao) : MarketLocalDataSource {

    override fun save(marketCache: MarketCache): Completable = marketDao.save(marketCache)

    override fun getAllFrom(coin: String): Flowable<MarketCache> = marketDao.getMarketsFrom(coin)
}