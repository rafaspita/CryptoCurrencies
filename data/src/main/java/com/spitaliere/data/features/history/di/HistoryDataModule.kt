package com.spitaliere.data.features.history.di

import com.spitaliere.data.features.history.datasource.local.HistoryLocalDataSource
import com.spitaliere.data.features.history.datasource.local.HistoryLocalDataSourceImpl
import com.spitaliere.data.features.history.datasource.remote.HistoryRemoteDataSource
import com.spitaliere.data.features.history.datasource.remote.HistoryRemoteDataSourceImpl
import com.spitaliere.data.features.history.repository.HistoryRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import com.spitaliere.domain.features.history.repository.HistoryRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object HistoryDataModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).historyDao() }
        factory<HistoryLocalDataSource>{ HistoryLocalDataSourceImpl(historyDao = get()) }
        factory<HistoryRemoteDataSource>{ HistoryRemoteDataSourceImpl(coinCapApi = get()) }
        factory<HistoryRepository>{
            HistoryRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get()
            )
        }
    }
}