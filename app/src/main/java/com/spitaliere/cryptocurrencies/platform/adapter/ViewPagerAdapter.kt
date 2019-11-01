package com.spitaliere.cryptocurrencies.platform.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spitaliere.cryptocurrencies.platform.base.BaseFragment

/**
 * Created by Rafael Spitaliere on 19/10/2019.
 **/
class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList : List<BaseFragment<*, *>> = listOf()
    var fragmentListName : List<String> = listOf()

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = fragmentListName[position]

}