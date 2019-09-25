package com.spitaliere.domain.features.market.repository

import com.spitaliere.domain.features.market.entity.Market
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface MarketRepository {

    fun updateCoinMarkets(coin: String): Completable

    fun getCoinMarkets(coin: String): Flowable<Market>
}