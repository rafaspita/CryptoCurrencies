package com.spitaliere.data.features.history.repository

import com.spitaliere.data.features.history.datasource.local.HistoryLocalDataSource
import com.spitaliere.data.features.history.datasource.remote.HistoryRemoteDataSource
import com.spitaliere.data.features.history.entity.mapToDomain
import com.spitaliere.domain.features.history.entity.History
import com.spitaliere.domain.features.history.repository.HistoryRepository
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class HistoryRepositoryImpl(
    private val remoteDataSource: HistoryRemoteDataSource,
    private val localDataSource: HistoryLocalDataSource
) : HistoryRepository {

    override fun updateCoinHistory(path: String, interval: String): Completable = remoteDataSource.getHistory(path, interval).flatMapCompletable{ localDataSource.save(it)}

    override fun getCoinHistory(coinName: String): Flowable<History> = localDataSource.getAllFrom(coinName).map { it.mapToDomain() }
}