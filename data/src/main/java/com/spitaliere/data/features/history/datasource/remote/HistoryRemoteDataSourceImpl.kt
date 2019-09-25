package com.spitaliere.data.features.history.datasource.remote

import com.spitaliere.data.api.CoinCapApi
import com.spitaliere.data.features.history.entity.HistoryCache
import com.spitaliere.domain.platform.extension.getTodayZeroHour
import io.reactivex.Single
import java.util.*

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class HistoryRemoteDataSourceImpl(private val coinCapApi: CoinCapApi) : HistoryRemoteDataSource {

    override fun getHistory(path: String, interval: String): Single<HistoryCache> =
        coinCapApi.fetchHistory(
            path,
            interval,
            Calendar.getInstance().getTodayZeroHour(),
            Calendar.getInstance().timeInMillis
        ).map { HistoryCache(coinName = path, historyCache = it.data) }
}