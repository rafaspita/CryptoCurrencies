package com.spitaliere.domain.features.currency.di

import com.spitaliere.domain.features.currency.usecase.GetAllCurrenciesCase
import com.spitaliere.domain.features.currency.usecase.SyncCurrenciesCase
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 25/09/2019.
 **/
object CurrencyDomainModule {

    fun getModule() = module {
        factory{ GetAllCurrenciesCase(currencyRepository = get()) }
        factory{ SyncCurrenciesCase(currencyRepository = get()) }
    }
}