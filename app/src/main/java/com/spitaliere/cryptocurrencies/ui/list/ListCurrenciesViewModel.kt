package com.spitaliere.cryptocurrencies.ui.list

import androidx.lifecycle.MutableLiveData
import com.spitaliere.cryptocurrencies.platform.base.BaseViewModel
import com.spitaliere.cryptocurrencies.platform.extension.invokeOnBackground
import com.spitaliere.cryptocurrencies.platform.viewstate.StateMachineCompletable
import com.spitaliere.cryptocurrencies.platform.viewstate.ViewState
import com.spitaliere.cryptocurrencies.ui.list.dialog.SortDialog
import com.spitaliere.domain.features.currency.entity.Currency
import com.spitaliere.domain.features.currency.usecase.GetAllCurrenciesCase
import com.spitaliere.domain.features.currency.usecase.SyncCurrenciesCase
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class ListCurrenciesViewModel (
        private val getAllCurrenciesCase: GetAllCurrenciesCase,
        private val syncCurrenciesCase: SyncCurrenciesCase
): BaseViewModel() {

    val currenciesList: MutableLiveData<List<Currency>> = MutableLiveData()
    val syncList : MutableLiveData<ViewState<Nothing>> = MutableLiveData()
    val sortBy : MutableLiveData<SortDialog.SortBy> = MutableLiveData()

    override fun init() {
        getAllCurrenciesCase.invokeOnBackground()
                .subscribeBy(
                        onError = {it.printStackTrace()},
                        onNext = {currenciesList.value = sortList(sortBy.value, it)}
                ).addTo(disposables)
    }

    fun update(forceUpdate : Boolean = false){
        syncCurrenciesCase.invokeOnBackground(forceUpdate)
                .compose(StateMachineCompletable(syncList))
                .subscribeBy(
                        onError = {},
                        onComplete = {})
                .addTo(disposables)
    }

    fun setSort(sortByEnum: SortDialog.SortBy) {
        sortBy.value = sortByEnum

        currenciesList.value = sortList(sortByEnum, currenciesList.value)
    }

    private fun sortList(sortByEnum: SortDialog.SortBy?, list: List<Currency>?) : List<Currency>? {
       return when (sortByEnum) {
            SortDialog.SortBy.RANK -> list?.sortedBy { it.rank }
            SortDialog.SortBy.NAME -> list?.sortedBy { it.name }
            SortDialog.SortBy.PRICE -> list?.sortedByDescending { it.priceUsd }
            SortDialog.SortBy.CHANGE -> list?.sortedByDescending { it.changePercent24Hr }
            else -> list?.sortedBy { it.rank }
        }
    }
}