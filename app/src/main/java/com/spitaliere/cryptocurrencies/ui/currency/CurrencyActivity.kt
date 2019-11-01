package com.spitaliere.cryptocurrencies.ui.currency

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ActivityCurrencyBinding
import com.spitaliere.cryptocurrencies.platform.adapter.ViewPagerAdapter
import com.spitaliere.cryptocurrencies.platform.base.BaseActivity
import com.spitaliere.cryptocurrencies.ui.currency.fragments.details.DetailsFragment
import com.spitaliere.cryptocurrencies.ui.currency.fragments.history.HistoryFragment
import com.spitaliere.cryptocurrencies.ui.currency.fragments.market.MarketFragment
import com.spitaliere.domain.features.currency.entity.Currency
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class CurrencyActivity : BaseActivity<CurrencyViewModel, ActivityCurrencyBinding>() {

    override val viewModel: CurrencyViewModel by viewModel()
    private val detailsFragment: DetailsFragment by inject()
    private val historyFragment: HistoryFragment by inject()
    private val marketsFragment: MarketFragment by inject()
    private val currency by lazy { intent.extras?.get(CURRENCY_KEY) as Currency }

    companion object{
        const val CURRENCY_KEY : String = "Currency"

        fun getIntent(context: Context, currency: Currency) : Intent =
            Intent(context, CurrencyActivity::class.java).apply{ putExtra(CURRENCY_KEY, currency) }

    }

    override fun getLayout(): Int = R.layout.activity_currency

    override fun hasToolbar(): Boolean = true

    override fun getToolbarTitle(): String = currency.name

    override fun registerObservables() {
        viewModel.marketData.observe(this, Observer {
            marketsFragment.setMarkets(it)
        })
        viewModel.historyData.observe(this, Observer{
            historyFragment.setHistory(it)
        })
    }

    override fun setupView() {
        binding.currency = currency
        viewModel.currencyData.value = currency

        binding.nestedScroolView.isFillViewport = true

        binding.updatedOn.text = getString(R.string.updated_on, currency.getLastUpdateFormat())

        with(binding.currencyImage){
            Picasso.get()
                .load(getString(R.string.load_images_url, currency.symbol.toLowerCase(), "400"))
                .into(this)

            alpha = 0.2f
        }

        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.adapter = getViewPagerAdapter()
    }


    private fun getViewPagerAdapter() = ViewPagerAdapter(supportFragmentManager).apply {
        fragmentListName = listOf(
            getString(R.string.details),
            getString(R.string.history),
            getString(R.string.markets)
        )

        fragmentList = listOf(
            detailsFragment,
            historyFragment,
            marketsFragment
        )
    }
}