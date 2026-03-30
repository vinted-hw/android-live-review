package com.vinted.demovinted.features.feed.api

import com.vinted.demovinted.features.feed.api.responses.CatalogItemListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Api {

    @GET("items")
    suspend fun getItemsFeed(
        @QueryMap params: Map<String, String>
    ): CatalogItemListResponse

    companion object {
        const val KEY_PAGE = "page"
        const val KEY_PER_PAGE = "per_page"
        const val KEY_TIME = "time"
        const val STARTING_PAGE = 1
    }
}
