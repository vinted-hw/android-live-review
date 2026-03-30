package com.vinted.demovinted.features.itemdetails

import com.vinted.demovinted.features.feed.api.responses.CatalogItemListResponse
import com.vinted.demovinted.models.ItemBox

data class ItemDetailsState(
    val itemBox: ItemBox = ItemBox(),
    val catalogItemListResponse: CatalogItemListResponse = CatalogItemListResponse(),
    val items: List<ItemBox> = emptyList(),
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
)