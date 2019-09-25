package com.spitaliere.domain.platform.usecase

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
object Nothing

interface BaseUseCase<in Input, out Output>{
    fun run(input: Input) : Output
}

interface SingleUseCase<Input, Output> : BaseUseCase<Input, Single<Output>>

interface FlowableUseCase<Input, Output> : BaseUseCase<Input, Flowable<Output>>

interface ObservableUseCase<Input, Output> : BaseUseCase<Input, Observable<Output>>

interface CompletableUseCase<Input> : BaseUseCase<Input, Completable>