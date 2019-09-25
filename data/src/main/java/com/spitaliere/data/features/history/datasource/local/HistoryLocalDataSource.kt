package com.spitaliere.data.features.history.datasource.local

import com.spitaliere.data.features.history.entity.HistoryCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface HistoryLocalDataSource {

    fun save(historyCache: HistoryCache) : Completable

    fun getAllFrom(coin: String): Flowable<HistoryCache>
}