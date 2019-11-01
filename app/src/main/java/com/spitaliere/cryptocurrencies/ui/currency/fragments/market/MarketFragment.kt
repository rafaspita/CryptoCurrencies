package com.spitaliere.cryptocurrencies.ui.currency.fragments.market

import android.view.View
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.FragmentMarketsBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseFragment
import com.spitaliere.cryptocurrencies.platform.extension.assemble
import com.spitaliere.cryptocurrencies.ui.currency.CurrencyViewModel
import com.spitaliere.domain.features.market.entity.Market
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class MarketFragment : BaseFragment<CurrencyViewModel, FragmentMarketsBinding>() {

    override val viewModel: CurrencyViewModel by sharedViewModel()
    private val marketAdapter: MarketAdapter by inject()

    override fun getLayout(): Int = R.layout.fragment_markets

    override fun setupView(root: View?) {
        binding.recycler.assemble(application, marketAdapter)
    }

    fun setMarkets(market: Market){
        marketAdapter.list = market.markets.sortedByDescending {it.volumePercent}
    }
}