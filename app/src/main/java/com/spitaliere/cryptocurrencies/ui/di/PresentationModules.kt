package com.spitaliere.cryptocurrencies.ui.di

import com.spitaliere.cryptocurrencies.ui.currency.CurrencyModule
import com.spitaliere.cryptocurrencies.ui.list.ListCurrenciesModule
import com.spitaliere.cryptocurrencies.ui.selection.SelectionModule
import com.spitaliere.cryptocurrencies.ui.splashscreen.SplashScreenModule

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
object PresentationModules {

    fun getModules() = listOf(
        SplashScreenModule.getModule(),
        SelectionModule.getModule(),
        ListCurrenciesModule.getModule(),
        CurrencyModule.getModule()
    )
}