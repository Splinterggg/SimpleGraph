package com.app.simplegraph.data

import com.app.simplegraph.utils.Resource
import com.app.simplegraph.models.ApiResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DataSource @Inject constructor(
    private val bitcoinEndpoint: BitcoinEndpoint
) : BaseDataSource() {

    suspend fun getBitcoinPrices(
        start: String,
        end: String,
    ): Resource<ApiResult> {
        val result = coroutineScope {
            async {
                getResult {
                    bitcoinEndpoint.getBitcoinPriceForDay(
                        start,
                        end
                    )
                }
            }
        }
        return result.await()
    }
}