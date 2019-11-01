package com.spitaliere.cryptocurrencies.platform.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.spitaliere.cryptocurrencies.BR

/**
 * Created by Rafael Spitaliere on 06/10/2019.
 **/
abstract class BaseRecyclerViewAdapter <ENTITY, VDB : ViewDataBinding>(context: Context) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder<VDB>>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    var list: List<ENTITY> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickItem: ((ENTITY, view:VDB) -> Unit)? = null

    @LayoutRes
    protected abstract fun getLayout(): Int

    protected abstract fun areItemTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean

    protected abstract fun areContentTheSame(oldItem: ENTITY, newItem: ENTITY): Boolean

    open fun prepareView(item: ENTITY, binding: VDB, position: Int){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<VDB> {
        val binding : VDB = DataBindingUtil.inflate(layoutInflater, getLayout(), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<VDB>, position: Int) {
        val item : ENTITY = list[position]
        holder.binding.setVariable(BR.item, item)
        prepareView(item, holder.binding, position)
        holder.binding.executePendingBindings()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder<VDB : ViewDataBinding>(val binding: VDB) : RecyclerView.ViewHolder(binding.root)

}