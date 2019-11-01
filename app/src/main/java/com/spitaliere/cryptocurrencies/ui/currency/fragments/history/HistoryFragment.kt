package com.spitaliere.cryptocurrencies.ui.currency.fragments.history

import android.view.View
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.FragmentHistoryBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseFragment
import com.spitaliere.cryptocurrencies.ui.currency.CurrencyViewModel
import com.spitaliere.domain.features.history.entity.HistoryData
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class HistoryFragment : BaseFragment<CurrencyViewModel, FragmentHistoryBinding>() {

    override val viewModel: CurrencyViewModel by sharedViewModel()

    override fun getLayout(): Int = R.layout.fragment_history

    override fun setupView(root: View?) {

    }

    fun setHistory(list: List<HistoryData>) {
        ChartAdapter.buildLineChart(binding.lineChart, list, application)
    }
}