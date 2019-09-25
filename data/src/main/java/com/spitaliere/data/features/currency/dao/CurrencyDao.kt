package com.spitaliere.data.features.currency.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.currency.entity.CurrencyCache
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Dao
interface CurrencyDao {

    @Query("SELECT * FROM currency c INNER JOIN selection s ON (c.id = s.id) WHERE s.selected = '1'  ORDER BY rank ASC")
    fun getCurrencies(): Flowable<List<CurrencyCache>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(currencyCache: List<CurrencyCache>) : Completable

    @Query("DELETE FROM currency")
    fun deleteAll()
}