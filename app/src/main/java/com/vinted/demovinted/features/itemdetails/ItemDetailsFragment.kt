package com.vinted.demovinted.features.itemdetails

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.vinted.demovinted.core.currency.CurrencyFormatter
import com.vinted.demovinted.models.ItemBox
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    private val viewModel: ItemDetailsViewModel by viewModels()

    @Inject
    lateinit var currencyFormatter: CurrencyFormatter

    private fun navigateToItemsDetails(itemBox: ItemBox) {
        val fragment = newInstance(itemBox)

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
            setContent {
                ItemDetailsScreen(
                    viewModel = viewModel,
                    currencyFormatter = currencyFormatter,
                    onItemClick = ::navigateToItemsDetails,
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createdFragments.add(this)
        viewModel.init()
    }

    companion object {
        @JvmStatic
        private val createdFragments = mutableListOf<ItemDetailsFragment>()

        fun newInstance(item: ItemBox): ItemDetailsFragment {
            return ItemDetailsFragment().apply {
                arguments = Bundle().apply { putParcelable(ItemDetailsConstant.ARG_EXTRA_ITEM, item) }
            }
        }
    }
}
