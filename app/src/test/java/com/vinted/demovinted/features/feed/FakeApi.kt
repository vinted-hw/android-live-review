package com.vinted.demovinted.features.feed

import com.vinted.demovinted.models.FeedItem
import com.vinted.demovinted.features.feed.api.Api
import com.vinted.demovinted.features.feed.api.responses.CatalogItemListResponse

class FakeApi(
    var exception: Exception? = null,
) : Api {

    override suspend fun getItemsFeed(params: Map<String, String>): CatalogItemListResponse {
        val immutableException = exception
        if (immutableException != null) throw immutableException

        return RESPONSE_ONE
    }
}

internal val RESPONSE_ONE = CatalogItemListResponse(
    items = listOf(
        FeedItem(id = 1),
        FeedItem(id = 2),
        FeedItem(id = 3)
    ),
)