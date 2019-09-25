package com.spitaliere.data.features.history.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.history.entity.HistoryCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(historyCache: HistoryCache) : Completable

    @Query("SELECT * FROM history WHERE coinName == :coinName")
    fun getHistory(coinName : String) : Flowable<HistoryCache>
}