package com.spitaliere.cryptocurrencies.ui.currency.fragments.details

import android.view.View
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.FragmentDetailsBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseFragment
import com.spitaliere.cryptocurrencies.platform.extension.assemble
import com.spitaliere.cryptocurrencies.ui.currency.CurrencyViewModel
import com.spitaliere.domain.features.currency.entity.CurrencyDetail
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class DetailsFragment : BaseFragment<CurrencyViewModel, FragmentDetailsBinding>() {

    override val viewModel: CurrencyViewModel by sharedViewModel()
    private val currencyDetailAdapter: CurrencyDetailAdapter by inject()

    override fun getLayout(): Int = R.layout.fragment_details

    override fun setupView(root: View?) {
        binding.detailsRecycler.assemble(application, currencyDetailAdapter.apply { list = getList() })
    }

    private fun getList() : List<CurrencyDetail>{
        val currency = viewModel.currencyData.value!!
        val list = ArrayList<CurrencyDetail>()
        list.add(CurrencyDetail(getStringResource(R.string.rank), currency.rank.toString()))
        list.add(CurrencyDetail(getStringResource(R.string.symbol), currency.symbol))
        list.add(CurrencyDetail(getStringResource(R.string.supply), currency.getCoinSupply()))
        list.add(CurrencyDetail(getStringResource(R.string.max_supply), currency.getCoinMaxSupply()))
        list.add(CurrencyDetail(getStringResource(R.string.market_cap_usd), currency.getCoinMarketCapUsd()))
        list.add(CurrencyDetail(getStringResource(R.string.volume_24hrs_usd), currency.getCoinVolumeUsd24Hr()))
        list.add(CurrencyDetail(getStringResource(R.string.change_24hrs), currency.getChange24h()))
        return list
    }

    private fun getStringResource(res: Int) = application.getString(res)
}