package com.spitaliere.cryptocurrencies.ui.splashscreen

import android.animation.Animator
import androidx.lifecycle.Observer
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ActivitySplashScreenBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseActivity
import com.spitaliere.cryptocurrencies.ui.list.ListCurrenciesActivity
import com.spitaliere.cryptocurrencies.ui.selection.SelectionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : BaseActivity<SplashScreenViewModel, ActivitySplashScreenBinding>() {

    override val viewModel: SplashScreenViewModel by viewModel()

    override fun getLayout() = R.layout.activity_splash_screen

    override fun registerObservables() {
        viewModel.loadScreen.observe(this, Observer {
            it.observeState(
                success = { hasSelected -> setAnimatioListener(hasSelected)
                },
                failed = {
                    startActivity(getNextActivity(false))
                    finish()
                }
            )
        })
    }

    private fun setAnimatioListener(hasSelected: Boolean) {
        binding.animationView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
                startActivity(getNextActivity(hasSelected))
                finish()
            }
            override fun onAnimationEnd(p0: Animator?) {}
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationStart(p0: Animator?) {}
        })
    }

    private fun getNextActivity(hasSelection: Boolean) =
        if (hasSelection) SelectionActivity.getIntent(this, true) else ListCurrenciesActivity.getIntent(this)

}
