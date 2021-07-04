package com.app.simplegraph.data

import com.app.simplegraph.models.ApiResult
import com.app.simplegraph.models.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BitcoinEndpoint {

    @GET("time-series?")
    suspend fun getBitcoinPriceForDay(
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("interval") interval: String = "1d",
        @Query("columns") columns: String = "close",
    ): Response<ApiResult>

    companion object {
        val SERVER =
            "https://data.messari.io/api/v1/assets/bitcoin/metrics/price/"
    }
}