package com.vinted.demovinted.features.feed

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.vinted.demovinted.ui.theme.AppTheme
import com.vinted.demovinted.features.itemdetails.ItemDetailsFragment
import com.vinted.demovinted.models.ItemBox
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()

    private fun navigateToItemsDetails(itemBox: ItemBox) {
        val fragment = ItemDetailsFragment.newInstance(itemBox)

        parentFragmentManager.commit {
            replace(R.id.content, fragment)
            addToBackStack(null)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AppTheme {
                    FeedScreen(viewModel = viewModel, onItemClick = ::navigateToItemsDetails)
                }
            }
        }
    }

    companion object {
        fun newInstance() = FeedFragment()
    }
}
