package com.spitaliere.data.api

import com.spitaliere.data.api.response.ApiResponse
import com.spitaliere.data.features.currency.entity.CurrencyCache
import com.spitaliere.data.features.history.entity.HistoryCacheData
import com.spitaliere.data.features.market.entity.MarketCacheData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
interface CoinCapApi {

    @GET("assets")
    fun fetchCurrency(
        @Query("limit") limit:String,
        @Query("ids") ids: String
    ): Single<ApiResponse<CurrencyCache>>

    @GET("assets/{coin}/history")
    fun fetchHistory(
        @Path("coin") coin:String,
        @Query("interval") interval:String,
        @Query("start") start:Long,
        @Query("end") end:Long
    ): Single<ApiResponse<HistoryCacheData>>

    @GET("assets/{coin}/markets")
    fun fetchMarkets(
        @Path("coin") coin:String,
        @Query("id") id:String
    ): Single<ApiResponse<MarketCacheData>>
}