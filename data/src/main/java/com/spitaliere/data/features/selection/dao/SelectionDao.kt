package com.spitaliere.data.features.selection.dao

import androidx.paging.DataSource
import androidx.room.*
import com.spitaliere.data.features.selection.entity.SelectionCache
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Dao
interface SelectionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(list :List<SelectionCache>) : Completable

    @Update
    fun update(list: List<SelectionCache>): Completable

    @Query("SELECT * FROM selection ORDER BY selected DESC")
    fun getAll() : Single<List<SelectionCache>>

    @Query("SELECT id FROM selection WHERE selected = '1'")
    fun getSelected(): Single<List<String>>

    @Query("SELECT * FROM selection ORDER BY sort ASC")
    fun getAllSelections(): DataSource.Factory<Int, SelectionCache>

    @Query("SELECT * FROM selection WHERE coinName LIKE :name ORDER BY sort ASC")
    fun getAllSelectionsByName(name: String): DataSource.Factory<Int, SelectionCache>
}