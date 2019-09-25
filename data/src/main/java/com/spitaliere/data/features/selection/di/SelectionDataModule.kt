package com.spitaliere.data.features.selection.di

import com.spitaliere.data.features.selection.datasource.local.SelectionLocalDataSource
import com.spitaliere.data.features.selection.datasource.local.SelectionLocalDataSourceImpl
import com.spitaliere.data.features.selection.repository.SelectionRepositoryImpl
import com.spitaliere.data.platform.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object SelectionDataModule {

    fun getModule() = module {
        single { AppDataBase.createDataBase(androidContext()).selectionDao() }
        factory<SelectionLocalDataSource>{ SelectionLocalDataSourceImpl(selectionDao = get()) }
        factory {
            SelectionRepositoryImpl(
                localDataSource = get(),
                currencyRemoteDataSource = get()
            )
        }
    }
}