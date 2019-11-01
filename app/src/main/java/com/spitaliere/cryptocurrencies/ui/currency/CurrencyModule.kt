package com.spitaliere.cryptocurrencies.ui.currency

import com.spitaliere.cryptocurrencies.ui.currency.fragments.details.CurrencyDetailAdapter
import com.spitaliere.cryptocurrencies.ui.currency.fragments.details.DetailsFragment
import com.spitaliere.cryptocurrencies.ui.currency.fragments.history.HistoryFragment
import com.spitaliere.cryptocurrencies.ui.currency.fragments.market.MarketAdapter
import com.spitaliere.cryptocurrencies.ui.currency.fragments.market.MarketFragment
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
object CurrencyModule {

    fun getModule() = module {
        viewModel { CurrencyViewModel(get(), get(), get(), get())  }
        factory { DetailsFragment().apply { application = androidContext() } }
        factory { HistoryFragment().apply { application = androidContext() } }
        factory { MarketFragment().apply { application = androidContext() } }
        factory { MarketAdapter(androidContext()) }
        factory { CurrencyDetailAdapter(androidContext()) }
//        scope(named<CurrencyActivity>()) {
//
//        }
    }
}