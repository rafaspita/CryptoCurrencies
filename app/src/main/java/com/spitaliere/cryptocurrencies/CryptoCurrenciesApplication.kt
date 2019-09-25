package com.spitaliere.cryptocurrencies

import android.app.Application
import com.spitaliere.data.platform.di.DataModules
import com.spitaliere.domain.platform.di.DomainModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class CryptoCurrenciesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext( this@CryptoCurrenciesApplication)
            modules( modules =
                DataModules.getModules()
                + DomainModules.getModules()
            )
        }
    }

}