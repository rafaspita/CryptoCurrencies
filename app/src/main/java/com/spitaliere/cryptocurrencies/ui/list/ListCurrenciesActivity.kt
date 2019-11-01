package com.spitaliere.cryptocurrencies.ui.list

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ActivityListCurrenciesBinding
import com.spitaliere.cryptocurrencies.databinding.ItemCurrencyBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseActivity
import com.spitaliere.cryptocurrencies.platform.extension.assemble
import com.spitaliere.cryptocurrencies.platform.extension.hideFab
import com.spitaliere.cryptocurrencies.ui.currency.CurrencyActivity
import com.spitaliere.cryptocurrencies.ui.list.dialog.SortDialog
import com.spitaliere.cryptocurrencies.ui.selection.SelectionActivity
import com.spitaliere.domain.features.currency.entity.Currency
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class ListCurrenciesActivity : BaseActivity<ListCurrenciesViewModel, ActivityListCurrenciesBinding>() {

    override val viewModel: ListCurrenciesViewModel by viewModel()
    private val adapterCurrencies : ListCurrenciesAdapter by currentScope.inject()
    private val sortDialog : SortDialog by currentScope.inject()

    companion object{
        fun getIntent(context: Context) : Intent =
                Intent(context, ListCurrenciesActivity::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_list_currencies

    override fun showBackButton() = false

    override fun getToolbarTitle(): String = getString(R.string.app_name)

    override fun registerObservables() {
        with(viewModel) {
            currenciesList.observe(this@ListCurrenciesActivity, Observer {
                adapterCurrencies.list = it
                binding.recycler.scrollToPosition(0)
            })

            syncList.observe(this@ListCurrenciesActivity, Observer {
                it.observeState(
                        loading = {showLoading()},
                        completed = { showLoading(show = false) },
                        failed = {
                            showLoading(show = false)
                            Toast.makeText(this@ListCurrenciesActivity, getString(R.string.no_changes_to_sync), Toast.LENGTH_LONG).show()
                        }
                )
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_filter -> { sortDialog.build(this, viewModel.sortBy.value, action = viewModel::setSort).show()}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SelectionActivity.ID && resultCode == RESULT_OK){
            viewModel.update(forceUpdate = true)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun setupView(){
        viewModel.update(forceUpdate = true)

        with(adapterCurrencies){
            clickItem = { currency: Currency, view: ItemCurrencyBinding ->
                startActivity(
                        CurrencyActivity.getIntent(this@ListCurrenciesActivity, currency),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                                this@ListCurrenciesActivity,
                                Pair<View, String>(view.root, ViewCompat.getTransitionName(view.root))
                        ).toBundle()
                )
            }
        }

        with(binding){
            recycler.assemble(this@ListCurrenciesActivity, adapterCurrencies)
            recycler.hideFab(fab)
            swipeRefresh.setOnRefreshListener { viewModel?.update() }
            fab.setOnClickListener { startActivityForResult(SelectionActivity.getIntent(this@ListCurrenciesActivity), SelectionActivity.ID) }
        }
    }

    private fun showLoading(show: Boolean = true) {
        binding.swipeRefresh.isRefreshing = show
    }
}