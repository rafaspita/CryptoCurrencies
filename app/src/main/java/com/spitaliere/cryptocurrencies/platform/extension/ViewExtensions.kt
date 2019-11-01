package com.spitaliere.cryptocurrencies.platform.extension

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun RecyclerView.assemble(context: Context, adapter: RecyclerView.Adapter<*>){
    this.layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
    this.hasFixedSize()
    this.itemAnimator = object : RecyclerView.ItemAnimator() {
        override fun isRunning(): Boolean = false
        override fun animatePersistence(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo
        ): Boolean = false
        override fun runPendingAnimations() {}
        override fun endAnimation(item: RecyclerView.ViewHolder) {}
        override fun animateDisappearance(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo?
        ): Boolean = false
        override fun animateChange(
            oldHolder: RecyclerView.ViewHolder,
            newHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo,
            postLayoutInfo: ItemHolderInfo
        ): Boolean = false
        override fun animateAppearance(
            viewHolder: RecyclerView.ViewHolder,
            preLayoutInfo: ItemHolderInfo?,
            postLayoutInfo: ItemHolderInfo
        ): Boolean = false
        override fun endAnimations() {}
    }
}

fun RecyclerView.hideFab(fab: FloatingActionButton){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (dy > 0 || dy < 0 && fab.isShown)
                fab.hide()
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE)
                fab.show()
            super.onScrollStateChanged(recyclerView, newState)
        }
    })
}

fun View.visible(visible :Boolean = true){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}



