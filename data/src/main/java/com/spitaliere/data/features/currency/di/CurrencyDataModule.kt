package com.spitaliere.data.features.currency.di

import com.spitaliere.data.features.currency.datasource.local.CurrencyLocalDataSource
import com.spitaliere.data.features.currency.datasource.local.CurrencyLocalDataSourceImpl
import com.spitaliere.data.features.currency.datasource.remote.CurrencyRemoteDataSource
import com.spitaliere.data.features.currency.datasource.remote.CurrencyRemoteDataSourceImpl
import com.spitaliere.data.features.currency.repository.CurrencyRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import com.spitaliere.domain.features.currency.repository.CurrencyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object CurrencyDataModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).currencyDao() }
        factory<CurrencyRemoteDataSource>{ CurrencyRemoteDataSourceImpl(coinCapApi = get(), preferences = get()) }
        factory<CurrencyLocalDataSource>{ CurrencyLocalDataSourceImpl(currencyDao = get()) }
        factory<CurrencyRepository> { CurrencyRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get(),
            selectionLocal = get(),
            preferences = get()
        ) }
    }
}