package com.spitaliere.cryptocurrencies.ui.list

import com.spitaliere.cryptocurrencies.ui.list.dialog.SortDialog
import com.spitaliere.cryptocurrencies.ui.list.dialog.SortDialogImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
object ListCurrenciesModule {

    fun getModule() = module {
        viewModel { ListCurrenciesViewModel(get(), get())  }
        scope(named<ListCurrenciesActivity>()){
            scoped { ListCurrenciesAdapter(androidContext()) }
            scoped<SortDialog> { SortDialogImpl() }
        }
    }
}