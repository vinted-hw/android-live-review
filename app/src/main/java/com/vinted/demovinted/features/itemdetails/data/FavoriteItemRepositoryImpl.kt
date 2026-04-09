package com.vinted.demovinted.features.itemdetails.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteItemRepositoryImpl @Inject constructor() : FavoriteItemRepository {

    private val favoriteItems = mutableSetOf<String>()

    override fun toggleFavoriteItem(itemId: String): Boolean {
        val containsFavoriteItem = favoriteItems.contains(itemId)

        if (containsFavoriteItem) {
            favoriteItems.remove(itemId)
        } else {
            favoriteItems.add(itemId)
        }

        return !containsFavoriteItem
    }

    override fun isItemFavorite(itemId: String): Boolean = favoriteItems.contains(itemId)
}

