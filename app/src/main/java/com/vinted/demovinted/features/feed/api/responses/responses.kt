package com.vinted.demovinted.features.feed.api.responses

import com.vinted.demovinted.models.FeedItem

open class CatalogItemListResponse(
    val items: List<FeedItem> = emptyList(),
)
