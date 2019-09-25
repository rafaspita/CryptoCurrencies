package com.spitaliere.data.features.market.repository

import com.spitaliere.data.features.market.datasource.local.MarketLocalDataSource
import com.spitaliere.data.features.market.datasource.remote.MarketRemoteDataSource
import com.spitaliere.data.features.market.entity.mapToDomain
import com.spitaliere.domain.features.market.entity.Market
import com.spitaliere.domain.features.market.repository.MarketRepository
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class MarketRepositoryImpl(
    private val remoteDataSource: MarketRemoteDataSource,
    private val localDataSource: MarketLocalDataSource
) : MarketRepository {

    override fun updateCoinMarkets(coin: String): Completable = remoteDataSource.getCoinMarkets(coin.toLowerCase()).flatMapCompletable { localDataSource.save(it) }

    override fun getCoinMarkets(coin: String): Flowable<Market> = localDataSource.getAllFrom(coin).map { it.mapToDomain() }
}