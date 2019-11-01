package com.spitaliere.data.preferences

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object PreferencesModule {

    fun getModule() = module {
        single<Preferences>{ PreferencesImpl(androidContext().getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)) }
    }
}