package com.vinted.demovinted.features.itemdetails.data

interface FavoriteItemRepository {
    fun toggleFavoriteItem(itemId: String): Boolean

    fun isItemFavorite(itemId: String): Boolean
}