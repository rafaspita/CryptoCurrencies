package com.spitaliere.cryptocurrencies.ui.selection

import android.content.Context
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ItemSelectionBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseRecyclerViewAdapter
import com.spitaliere.domain.features.selection.entity.Selection
import com.squareup.picasso.Picasso

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class SelectionAdapter(private val context: Context) : BaseRecyclerViewAdapter<Selection, ItemSelectionBinding>(context) {

    override fun getLayout(): Int = R.layout.item_selection

    override fun areItemTheSame(oldItem: Selection, newItem: Selection): Boolean = true

    override fun areContentTheSame(oldItem: Selection, newItem: Selection): Boolean = true

    override fun prepareView(item: Selection, binding: ItemSelectionBinding, position: Int) {
        Picasso.get()
                .load(context.getString(R.string.load_images_url_coincap, item.coin.toLowerCase()))
                .into(binding.imageView)

        binding.checkBox.isChecked = item.selected

        binding.root.setOnClickListener {
            item.selected = !item.selected
            binding.checkBox.isChecked = item.selected
            notifyItemChanged(position)
        }
    }
}