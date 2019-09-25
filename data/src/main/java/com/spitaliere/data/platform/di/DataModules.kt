package com.spitaliere.data.platform.di

import com.spitaliere.data.api.di.ApiModule
import com.spitaliere.data.features.currency.di.CurrencyDataModule
import com.spitaliere.data.features.history.di.HistoryDataModule
import com.spitaliere.data.features.market.di.MarketDataModule
import com.spitaliere.data.features.selection.di.SelectionDataModule
import com.spitaliere.data.preferences.PreferencesModule

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object DataModules {

    fun getModules() = listOf(
        ApiModule.getModule(),
        PreferencesModule.getModule(),
        CurrencyDataModule.getModule(),
        HistoryDataModule.getModule(),
        MarketDataModule.getModule(),
        SelectionDataModule.getModule()
    )
}