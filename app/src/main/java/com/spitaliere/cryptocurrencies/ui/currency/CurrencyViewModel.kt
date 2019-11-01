package com.spitaliere.cryptocurrencies.ui.currency

import androidx.lifecycle.MutableLiveData
import com.spitaliere.cryptocurrencies.platform.base.BaseViewModel
import com.spitaliere.cryptocurrencies.platform.extension.invokeOnBackground
import com.spitaliere.domain.features.currency.entity.Currency
import com.spitaliere.domain.features.history.entity.HistoryData
import com.spitaliere.domain.features.history.usecase.GetCoinHistoryCase
import com.spitaliere.domain.features.history.usecase.SyncHistoryCase
import com.spitaliere.domain.features.market.entity.Market
import com.spitaliere.domain.features.market.usecase.GetCoinMarketsCase
import com.spitaliere.domain.features.market.usecase.SyncMarketsCase
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class CurrencyViewModel(private val getCoinHistoryCase: GetCoinHistoryCase,
                        private val syncHistoryCase: SyncHistoryCase,
                        private val getCoinMarketsCase: GetCoinMarketsCase,
                        private val syncMarketsCase: SyncMarketsCase
) : BaseViewModel() {

    val historyData: MutableLiveData<List<HistoryData>> = MutableLiveData()
    val marketData: MutableLiveData<Market> = MutableLiveData()
    val currencyData: MutableLiveData<Currency> = MutableLiveData()

    override fun init() {
        getMarkets(currencyData.value!!.id)
        getHistory(currencyData.value!!.id)
    }

    private fun getHistory(currencyName: String) {
        syncHistoryCase.invokeOnBackground(currencyName)
            .subscribeBy(
                onComplete = {},
                onError = {it.printStackTrace()}
            ).addTo(disposables)

        getCoinHistoryCase.invokeOnBackground(currencyName)
            .subscribeBy(
                onNext = {historyData.value = it.historyCache},
                onError = {it.printStackTrace()}
            ).addTo(disposables)
    }

    private fun getMarkets(coinName: String){
        syncMarketsCase.invokeOnBackground(coinName)
            .subscribeBy(
                onComplete = {},
                onError = {it.printStackTrace()}
            ).addTo(disposables)

        getCoinMarketsCase.invokeOnBackground(coinName)
            .subscribeBy(
                onNext = {marketData.value = it},
                onError = {}
            ).addTo(disposables)
    }
}