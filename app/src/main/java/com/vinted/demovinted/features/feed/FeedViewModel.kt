package com.vinted.demovinted.features.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinted.demovinted.core.currency.CurrencyFormatter
import com.vinted.demovinted.features.feed.api.Api
import com.vinted.demovinted.features.feed.api.Api.Companion.KEY_PAGE
import com.vinted.demovinted.features.feed.api.Api.Companion.KEY_PER_PAGE
import com.vinted.demovinted.features.feed.api.Api.Companion.KEY_TIME
import com.vinted.demovinted.models.FeedItem
import com.vinted.demovinted.models.ItemBox
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val api: Api,
    private val numberFormat: CurrencyFormatter,
) : ViewModel() {

    private val _feedState = MutableStateFlow(State())
    val feedState = _feedState.asStateFlow()

    private val _feedEvent = Channel<Event>()
    val feedEvent = _feedEvent.receiveAsFlow()

    private val parameters = createParameters()

    init {
        requestItems()
    }

    private fun requestItems() {
        _feedState.update { state -> state.copy(status = State.Status.Loading) }
        viewModelScope.launch {
            try {
                val result = api.getItemsFeed(parameters)
                _feedState.update { state ->
                    state.copy(
                        status = State.Status.Success,
                        content = result.items.mapToUniqueItemBox())
                }
            } catch (e: Exception) {
                _feedEvent.send(Event.Error(e))
            }
        }
    }

    private fun List<FeedItem>.mapToUniqueItemBox(): List<ItemBox> {
        val currentItems = feedState.value.content
        val result = this.map { feedItem ->
            ItemBox.fromFeedItem(feedItem, numberFormat)
        }
        val itemIds = currentItems.map { it.itemId }
        return currentItems + result.filterNot { itemIds.contains(it.itemId) }
    }

    private fun createParameters(): Map<String, String> {
        val params = HashMap<String, String>()
        params[KEY_PAGE] = "1"
        params[KEY_PER_PAGE] = "20"
        params[KEY_TIME] = "${System.currentTimeMillis()}"
        return params
    }

    data class State(
        val status: Status = Status.Loading,
        val content: List<ItemBox> = emptyList()
    ) {

        sealed class Status {
            data object Loading : Status()
            data object Success : Status()
        }
    }

    sealed class Event {
        data class Error(val t: Throwable) : Event()
    }
}
