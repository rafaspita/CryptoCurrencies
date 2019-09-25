package com.spitaliere.data.features.history.datasource.local

import com.spitaliere.data.features.history.dao.HistoryDao
import com.spitaliere.data.features.history.entity.HistoryCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class HistoryLocalDataSourceImpl(private val historyDao: HistoryDao) : HistoryLocalDataSource {

    override fun save(historyCache: HistoryCache): Completable = historyDao.save(historyCache)

    override fun getAllFrom(coin: String): Flowable<HistoryCache> = historyDao.getHistory(coin)
}