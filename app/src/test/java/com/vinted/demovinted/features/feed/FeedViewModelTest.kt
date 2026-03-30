package com.vinted.demovinted.features.feed

import app.cash.turbine.test
import com.vinted.demovinted.core.currency.CurrencyFormatter
import com.vinted.demovinted.DispatcherRule
import com.vinted.demovinted.models.ItemBox
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.Locale
import kotlin.IllegalStateException

class FeedViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    private val fakeApi = FakeApi()
    private val inMemoryFormatter: CurrencyFormatter = CurrencyFormatter {
        if (it == null) "" else "%.2f \u20AC".format(Locale.ENGLISH, it)
    }
    private fun createFixture() = FeedViewModel(api = fakeApi, numberFormat = inMemoryFormatter)

        @Test
        fun `initial requestMore invoke, emitted event is Loading with false and true`() = runTest {
            val exception = IllegalStateException("Bummmm! :bomb")
            fakeApi.exception = exception
            val fixture = createFixture()

            fixture.feedEvent.test {
                val eventReceived = expectMostRecentItem()
                assertTrue(eventReceived is FeedViewModel.Event.Error)
                assertEquals((eventReceived as FeedViewModel.Event.Error).t, exception)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `initial requestMore yields in a list of ItemBoxViewEntity with correct ids`() = runTest {
        val fixture = createFixture()
        val expected = listOf(
            ItemBox(itemId = "1"),
            ItemBox(itemId = "2"),
            ItemBox(itemId = "3"),
        )

        fixture.feedState.test {
            val content = expectMostRecentItem().content
            assertEquals(expected.map { it.itemId }, content.map { it.itemId })
        }
    }

    @Test
    fun `requestMore yields in a list of ItemBoxViewEntity with price formatted`() = runTest {
        val fixture = createFixture()
        val expected = listOf(
            ItemBox(itemId = "1", formattedPrice = "1.00 €"),
            ItemBox(itemId = "2", formattedPrice = "1.00 €"),
            ItemBox(itemId = "3", formattedPrice = "1.00 €"),
        )

        fixture.feedState.test {
            val content = expectMostRecentItem().content
            assertEquals(expected.map { it.formattedPrice }, content.map { it.formattedPrice })
        }
    }
}