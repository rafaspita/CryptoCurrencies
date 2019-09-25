package com.spitaliere.domain.platform.di

import com.spitaliere.domain.features.currency.di.CurrencyDomainModule
import com.spitaliere.domain.features.history.di.HistoryDomainModule
import com.spitaliere.domain.features.market.di.MarketDomainModule
import com.spitaliere.domain.features.selection.di.SelectionDomainModule

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
object DomainModules {

    fun getModules() = listOf(
        CurrencyDomainModule.getModule(),
        HistoryDomainModule.getModule(),
        MarketDomainModule.getModule(),
        SelectionDomainModule.getModule()
    )
}