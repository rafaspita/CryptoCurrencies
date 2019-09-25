package com.spitaliere.data.features.history.datasource.remote

import com.spitaliere.data.features.history.entity.HistoryCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface HistoryRemoteDataSource {

    fun getHistory(path: String, interval: String) : Single<HistoryCache>
}