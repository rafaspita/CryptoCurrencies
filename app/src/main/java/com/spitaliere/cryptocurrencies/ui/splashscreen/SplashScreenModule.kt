package com.spitaliere.cryptocurrencies.ui.splashscreen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
object SplashScreenModule{

    fun getModule() = module {
        viewModel { SplashScreenViewModel(get())  }
    }
}