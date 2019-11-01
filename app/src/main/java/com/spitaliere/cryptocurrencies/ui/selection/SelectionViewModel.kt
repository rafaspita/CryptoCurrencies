package com.spitaliere.cryptocurrencies.ui.selection

import androidx.lifecycle.MutableLiveData
import com.spitaliere.cryptocurrencies.platform.base.BaseViewModel
import com.spitaliere.cryptocurrencies.platform.extension.invokeOnBackground
import com.spitaliere.cryptocurrencies.platform.viewstate.StateMachineCompletable
import com.spitaliere.cryptocurrencies.platform.viewstate.StateMachineSingle
import com.spitaliere.cryptocurrencies.platform.viewstate.ViewState
import com.spitaliere.domain.features.selection.entity.Selection
import com.spitaliere.domain.features.selection.usecase.GetAllSelectionsCase
import com.spitaliere.domain.features.selection.usecase.UpdateSelectionCase
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class SelectionViewModel(
    private val getAllSelectionsCase: GetAllSelectionsCase,
    private val updateSelectionCase: UpdateSelectionCase
) : BaseViewModel() {

    val selectionsObservable = MutableLiveData<ViewState<List<Selection>>>()
    val finishObservable = MutableLiveData<ViewState<Nothing>>()

    override fun init() {
        getAllSelectionsCase.invokeOnBackground()
            .compose(StateMachineSingle(selectionsObservable))
            .subscribeBy(
                onError = {},
                onSuccess = {}
            ).addTo(disposables)
    }

    fun saveSelections(list:List<Selection>){
        updateSelectionCase.invokeOnBackground(list)
            .compose(StateMachineCompletable(finishObservable))
            .subscribeBy(
                onError = {},
                onComplete = {}
            )
            .addTo(disposables)
    }






}