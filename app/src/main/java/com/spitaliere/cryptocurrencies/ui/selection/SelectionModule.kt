package com.spitaliere.cryptocurrencies.ui.selection

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
object SelectionModule {

    fun getModule() = module {
        viewModel { SelectionViewModel(get(), get())  }
        scope(named<SelectionActivity>()){
            scoped { SelectionAdapter(androidContext()) }
        }
    }
}