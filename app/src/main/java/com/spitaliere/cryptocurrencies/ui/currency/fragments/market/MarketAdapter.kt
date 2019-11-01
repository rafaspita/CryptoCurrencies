package com.spitaliere.cryptocurrencies.ui.currency.fragments.market

import android.content.Context
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ItemMarketBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseRecyclerViewAdapter
import com.spitaliere.domain.features.market.entity.MarketData

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class MarketAdapter(context: Context) : BaseRecyclerViewAdapter<MarketData, ItemMarketBinding>(context) {

    override fun getLayout(): Int = R.layout.item_market

    override fun areItemTheSame(oldItem: MarketData, newItem: MarketData): Boolean = true

    override fun areContentTheSame(oldItem: MarketData, newItem: MarketData): Boolean = true

}