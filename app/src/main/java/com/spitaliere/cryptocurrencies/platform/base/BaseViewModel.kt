package com.spitaliere.cryptocurrencies.platform.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
abstract class BaseViewModel: ViewModel() {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()

        super.onCleared()
    }

    open fun init(){}
}