package com.spitaliere.cryptocurrencies.platform.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.spitaliere.cryptocurrencies.BR

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding : VB

    lateinit var application: Context

    @LayoutRes
    protected abstract fun getLayout() : Int

    protected open fun getBindableVariable(): Int = BR.viewModel

    protected open fun registerObservables(){}

    protected abstract fun setupView(root: View?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.setVariable(getBindableVariable(), viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        setupView(binding.root)
        registerObservables()
        return binding.root
    }
}