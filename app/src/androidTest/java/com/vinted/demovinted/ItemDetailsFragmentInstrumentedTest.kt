package com.vinted.demovinted

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.filters.LargeTest
import com.vinted.demovinted.application.MainActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ItemDetailsFragmentInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val DEFAULT_PAGE_SIZE = 20

    @Test
    @ExperimentalTestApi
    fun clickFeedItem_navigateToItemDetailFragment() {
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "feed_item"),
            timeoutMillis = 20_000,
        )
        composeTestRule.onAllNodesWithTag("feed_item")[0].performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "item_details_container"),
            timeoutMillis = DEFAULT_PAGE_SIZE * 1000L,
        )
        composeTestRule.onNodeWithTag("item_details_container").assertExists()
    }

    @Test
    @ExperimentalTestApi
    fun clickFavoriteButton_favoriteItem() {
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "feed_item"),
            timeoutMillis = 20_000,
        )
        composeTestRule.onAllNodesWithTag("feed_item")[0].performScrollTo().performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "button_favorite"),
            timeoutMillis = 10_000,
        )
        composeTestRule.onNodeWithTag("button_favorite").assertExists().performClick()
        composeTestRule.onNodeWithTag("button_favorite").performClick()
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "icon_favorite"),
            timeoutMillis = 10_000,
        )
        composeTestRule.onNodeWithTag("button_favorite").assertExists()
    }
}
