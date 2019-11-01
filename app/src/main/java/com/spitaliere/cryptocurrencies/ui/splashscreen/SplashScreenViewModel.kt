package com.spitaliere.cryptocurrencies.ui.splashscreen

import androidx.lifecycle.MutableLiveData
import com.spitaliere.cryptocurrencies.platform.base.BaseViewModel
import com.spitaliere.cryptocurrencies.platform.extension.invokeOnBackground
import com.spitaliere.cryptocurrencies.platform.viewstate.StateMachineSingle
import com.spitaliere.cryptocurrencies.platform.viewstate.ViewState
import com.spitaliere.domain.features.selection.usecase.SyncCurrenciesForSelectionCase
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
class SplashScreenViewModel(private val syncCurrenciesForSelection: SyncCurrenciesForSelectionCase) : BaseViewModel() {

    val loadScreen : MutableLiveData<ViewState<Boolean>> = MutableLiveData()

    override fun init() {
        syncCurrenciesForSelection.invokeOnBackground()
            .compose(StateMachineSingle(loadScreen))
            .subscribeBy(onError = {})
            .addTo(disposables)
    }

}