package com.spitaliere.data.features.market.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.market.entity.MarketCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Dao
interface MarketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(marketCache: MarketCache) : Completable

    @Query("SELECT * FROM market WHERE coinName = :coin")
    fun getMarketsFrom(coin: String): Flowable<MarketCache>
}