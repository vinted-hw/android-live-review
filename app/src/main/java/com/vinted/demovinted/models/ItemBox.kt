package com.vinted.demovinted.models

import android.os.Parcelable
import com.vinted.demovinted.core.currency.CurrencyFormatter
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class ItemBox(
    val itemId: String = "",
    val mainPhoto: Photo? = null,
    val price: BigDecimal = BigDecimal.ZERO,
    val formattedPrice: String = "",
    val category: String = "",
    val brandTitle: String? = null,
    val size: String? = null,
) : Parcelable {

    companion object {

        fun fromFeedItem(
            catalogItem: FeedItem,
            currencyFormatter: CurrencyFormatter,
        ): ItemBox {
            return ItemBox(
                itemId = catalogItem.id.toString(),
                mainPhoto = catalogItem.mainPhoto,
                price = catalogItem.price,
                formattedPrice = currencyFormatter.format(catalogItem.price).toString(),
                brandTitle = catalogItem.itemBrand.title,
                category = catalogItem.category
            )
        }
    }
}
