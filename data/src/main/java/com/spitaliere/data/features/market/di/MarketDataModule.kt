package com.spitaliere.data.features.market.di

import com.spitaliere.data.features.market.datasource.local.MarketLocalDataSource
import com.spitaliere.data.features.market.datasource.local.MarketLocalDataSourceImpl
import com.spitaliere.data.features.market.datasource.remote.MarketRemoteDataSource
import com.spitaliere.data.features.market.datasource.remote.MarketRemoteDataSourceImpl
import com.spitaliere.data.features.market.repository.MarketRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import com.spitaliere.domain.features.market.repository.MarketRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object MarketDataModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).marketDao() }
        factory<MarketRemoteDataSource>{ MarketRemoteDataSourceImpl(coinCapApi = get()) }
        factory<MarketLocalDataSource>{ MarketLocalDataSourceImpl(marketDao = get()) }
        factory<MarketRepository>{
            MarketRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get()
            )
        }
    }
}