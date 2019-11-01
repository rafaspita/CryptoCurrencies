package com.spitaliere.cryptocurrencies.platform.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.spitaliere.cryptocurrencies.BR
import com.spitaliere.cryptocurrencies.R

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding>: AppCompatActivity() {

    protected abstract val viewModel: VM
    protected lateinit var binding : VB

    @LayoutRes
    protected abstract fun getLayout() : Int

    protected open fun getToolbarTitle() : String = ""

    protected open fun showBackButton(): Boolean = true

    protected open fun hasToolbar(): Boolean = false

    protected open fun getBindVar(): Int = BR.viewModel

    protected open fun registerObservables(){}

    protected open fun setupView(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingView()
        setToolbar()
        setupView()
        registerObservables()
        viewModel.init()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setToolbar() {
        if (hasToolbar()) setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getToolbarTitle()
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton())
    }


    private fun setBindingView() {
        binding = DataBindingUtil.setContentView(this, getLayout())
        binding.setVariable(getBindVar(), viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }
}