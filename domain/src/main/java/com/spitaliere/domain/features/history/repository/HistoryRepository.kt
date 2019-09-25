package com.spitaliere.domain.features.history.repository

import com.spitaliere.domain.features.history.entity.History
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 24/09/2019.
 **/
interface HistoryRepository {

    fun updateCoinHistory(path: String, interval: String): Completable

    fun getCoinHistory(coinName: String): Flowable<History>
}