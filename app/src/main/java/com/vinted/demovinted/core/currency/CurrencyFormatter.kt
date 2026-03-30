package com.vinted.demovinted.core.currency

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import javax.inject.Inject

fun interface CurrencyFormatter {

    fun format(
        amount: BigDecimal?,
    ): CharSequence
}

class DefaultCurrencyFormatter @Inject constructor() : CurrencyFormatter {

    override fun format(amount: BigDecimal?): CharSequence {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.ENGLISH)
        val currency = try {
            Currency.getInstance(Locale.ENGLISH)
        } catch (e: Exception) {
            Currency.getInstance("EUR")
        }
        numberFormat.currency = currency

        return numberFormat.format(amount)
    }
}
