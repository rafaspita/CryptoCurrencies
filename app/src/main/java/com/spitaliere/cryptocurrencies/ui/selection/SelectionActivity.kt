package com.spitaliere.cryptocurrencies.ui.selection

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ActivitySelectionBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseActivity
import com.spitaliere.cryptocurrencies.platform.extension.assemble
import com.spitaliere.cryptocurrencies.ui.list.ListCurrenciesActivity
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class SelectionActivity: BaseActivity<SelectionViewModel, ActivitySelectionBinding>() {

    override val viewModel: SelectionViewModel by viewModel()
    private val selectionAdapter : SelectionAdapter by currentScope.inject()
    private val fromSplash by lazy { intent.getBooleanExtra(FROM_SPLASH, false) }

    companion object{
        const val ID = 1001
        const val FROM_SPLASH = "from_splash"
        fun getIntent(context: Context, fromSplash: Boolean = false) =
            Intent(context, SelectionActivity::class.java).apply { putExtra(FROM_SPLASH, fromSplash) }
    }

    override fun getLayout(): Int = R.layout.activity_selection

    override fun getToolbarTitle(): String  = getString(R.string.selection_title)

    override fun showBackButton(): Boolean = !fromSplash

    override fun registerObservables() {
        viewModel.selectionsObservable.observe(this, Observer { viewState ->
            viewState.observeState(
                loading = {},
                success = {selectionAdapter.list = it}
            )
        })

        viewModel.finishObservable.observe(this, Observer { viewState ->
            viewState.observeState(
                loading = {},
                completed = {
                    takeIf { fromSplash }?.apply { startActivity(ListCurrenciesActivity.getIntent(this)) }
                    finish()
                }
            )
        })
    }

    override fun setupView() {
        with(binding){
            recycler.assemble(this@SelectionActivity, selectionAdapter)
            saveButton.setOnClickListener{
                viewModel.saveSelections(selectionAdapter.list)
                setResult(RESULT_OK)
            }
        }


    }


}