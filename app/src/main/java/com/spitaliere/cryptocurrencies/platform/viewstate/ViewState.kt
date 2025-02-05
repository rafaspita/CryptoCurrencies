package com.spitaliere.cryptocurrencies.platform.viewstate

import androidx.lifecycle.MutableLiveData
import io.reactivex.*

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    object Completed : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
    data class Failed(val throwable: Throwable) : ViewState<Nothing>()

    fun observeState(
        loading: () -> Unit = {},
        completed: ()-> Unit = {},
        failed: (throwable: Throwable) -> Unit = {},
        success: ( data: T) -> Unit = {}
    ){
        when(this){
            is Loading -> { loading() }
            is Completed -> { completed() }
            is Failed -> { failed(this.throwable) }
            is Success -> {success(this.data)}
        }
    }
}

class StateMachineSingle<T>(private val viewState: MutableLiveData<ViewState<T>>):
    SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream
            .doOnSuccess{ viewState.value = ViewState.Success(it as T) }
            .doOnError { viewState.value = ViewState.Failed(it) }
            .doOnSubscribe { viewState.value = ViewState.Loading }
    }
}

class StateMachineCompletable<T>(private val viewState: MutableLiveData<ViewState<T>>) :
    CompletableTransformer {

    override fun apply(upstream: Completable): CompletableSource {
        return upstream
            .doOnSubscribe { viewState.value = ViewState.Loading }
            .doOnComplete { viewState.value = ViewState.Completed }
            .doOnError { viewState.value = ViewState.Failed(it) }
    }

}