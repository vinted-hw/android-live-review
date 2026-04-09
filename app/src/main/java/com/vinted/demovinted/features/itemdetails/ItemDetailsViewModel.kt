package com.vinted.demovinted.features.itemdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.vinted.demovinted.core.currency.DefaultCurrencyFormatter
import com.vinted.demovinted.features.feed.api.Api
import com.vinted.demovinted.features.feed.api.Api.Companion.KEY_PAGE
import com.vinted.demovinted.models.ItemBox
import com.vinted.demovinted.features.itemdetails.data.FavoriteItemRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDetailsViewModel @Inject constructor(
    private val api: Api,
    private val favoriteItemRepository: FavoriteItemRepositoryImpl,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val viewModelCoroutineScope = CoroutineScope(Dispatchers.IO)

    private val _state = MutableStateFlow(ItemDetailsState(itemBox = itemBox))
    val state = _state.asStateFlow()

    private val itemBox get() = savedStateHandle.get<ItemBox>(ItemDetailsConstant.ARG_EXTRA_ITEM)!!

    fun init() {
        viewModelCoroutineScope.launch {
            _state.update { it.copy(isLoading = true) }

            val itemsFeed = async {
                api.getItemsFeed(
                    mutableMapOf<String, String>().apply {
                        put(KEY_PAGE, INITIAL_PAGE.toString())
                    },
                )
            }

            supervisorScope {
                setFavoriteItem(itemBox.itemId)
            }

            try {
                withContext(Dispatchers.Main) {
                    val catalogItemListResponse = itemsFeed.await()
                    val newItemBoxes = catalogItemListResponse.items.map { catalogItem ->
                        ItemBox.fromFeedItem(catalogItem, DefaultCurrencyFormatter())
                    }
                    _state.update {
                        it.copy(
                            isLoading = false,
                            catalogItemListResponse = catalogItemListResponse,
                            items = it.items + newItemBoxes,
                        )
                    }
                }
            } catch (_: Exception) {
            }
        }
    }

    private fun setFavoriteItem(itemId: String) {
        val isFavorite = favoriteItemRepository.isItemFavorite(itemId)

        _state.update { it.copy(isFavorite = isFavorite) }
    }

    fun toggleFavoritePersist(itemId: String) {
        viewModelCoroutineScope.launch {
            favoriteItemRepository.toggleFavoriteItem(itemId)
        }
    }

    fun syncFavoriteState(itemId: String) {
        viewModelCoroutineScope.launch {
            setFavoriteItem(itemId)
        }
    }

    companion object {

        private const val INITIAL_PAGE = 0
    }
}

