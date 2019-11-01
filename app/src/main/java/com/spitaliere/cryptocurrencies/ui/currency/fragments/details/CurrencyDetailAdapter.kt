package com.spitaliere.cryptocurrencies.ui.currency.fragments.details

import android.content.Context
import androidx.core.content.ContextCompat
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ItemCurrencyDetailBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseRecyclerViewAdapter
import com.spitaliere.domain.features.currency.entity.CurrencyDetail

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class CurrencyDetailAdapter(private val context: Context) : BaseRecyclerViewAdapter<CurrencyDetail, ItemCurrencyDetailBinding>(context) {

    override fun getLayout(): Int = R.layout.item_currency_detail

    override fun areItemTheSame(oldItem: CurrencyDetail, newItem: CurrencyDetail): Boolean = true

    override fun areContentTheSame(oldItem: CurrencyDetail, newItem: CurrencyDetail): Boolean = true

    override fun prepareView(item: CurrencyDetail, binding: ItemCurrencyDetailBinding, position: Int) {

        with(binding.background){
            val color = if( position % 2 == 0)  R.color.colorPrimary else R.color.colorPrimaryLight
            setBackgroundColor(ContextCompat.getColor(context, color))
        }
    }

}