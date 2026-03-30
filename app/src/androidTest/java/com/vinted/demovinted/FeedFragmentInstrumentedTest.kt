package com.vinted.demovinted

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.performScrollTo
import androidx.test.filters.LargeTest
import com.vinted.demovinted.application.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@LargeTest
class FeedFragmentInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    @ExperimentalTestApi
    fun onFragmentDisplayed_checkItemCount_scrollToBottomLastItem() {
        composeTestRule.waitUntilAtLeastOneExists(
            matcher = hasTestTag(testTag = "feed_item"),
            timeoutMillis = 20_000,
        )

        composeTestRule.onAllNodesWithTag("feed_item").fetchSemanticsNodes().isNotEmpty()

        composeTestRule.onAllNodesWithTag("feed_item").onLast().performScrollTo()
        composeTestRule.onAllNodesWithTag("feed_item").onLast().isDisplayed()
    }
}
