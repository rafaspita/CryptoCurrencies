package com.spitaliere.domain.features.market.di

import com.spitaliere.domain.features.market.usecase.GetCoinMarketsCase
import com.spitaliere.domain.features.market.usecase.SyncMarketsCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
object MarketDomainModule {

    fun getModule() = module {
        factory{ GetCoinMarketsCase(marketRepository = get()) }
        factory{ SyncMarketsCase(marketRepository = get()) }
    }
}