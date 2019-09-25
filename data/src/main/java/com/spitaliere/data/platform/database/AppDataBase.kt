package com.spitaliere.data.platform.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spitaliere.data.features.currency.dao.CurrencyDao
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.data.features.history.dao.HistoryDao
import com.spitaliere.data.features.history.entity.HistoryCache
import com.spitaliere.data.features.market.dao.MarketDao
import com.spitaliere.data.features.market.entity.MarketCache
import com.spitaliere.data.features.selection.dao.SelectionDao
import com.spitaliere.data.features.selection.entity.SelectionCache
import com.spitaliere.data.platform.converter.HistoryConverter
import com.spitaliere.data.platform.converter.MarketConverter

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Database(
    version = 1,
    entities = [
        CurrencyCache::class,
        SelectionCache::class,
        MarketCache::class,
        HistoryCache::class
    ],
    exportSchema = true
)
@TypeConverters(value = [
    MarketConverter::class,
    HistoryConverter::class
])
abstract class AppDataBase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun selectionDao(): SelectionDao
    abstract fun marketDao(): MarketDao
    abstract fun historyDao() : HistoryDao

    companion object{
        private var instance: AppDataBase? = null

        @Synchronized
        fun createDataBase(context: Context) : AppDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "CRYPTO_CURRENCY_DATABASE.db"
                ).build()
            }

            return instance as AppDataBase
        }
    }
}