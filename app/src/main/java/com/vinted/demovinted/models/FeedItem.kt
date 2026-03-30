package com.vinted.demovinted.models

import java.math.BigDecimal

class FeedItem(
    val id: Int = 0,
    val price: BigDecimal = BigDecimal.ONE,
    private val photo: String = "",
    private val brand: String = "",
    val category: String = ""
)  {

    val mainPhoto: Photo
        get() = Photo(url = "https://mobile-homework-api.vinted.com/images/$photo")

    val itemBrand: ItemBrand
        get() = ItemBrand.createNoBrand(brand)
}