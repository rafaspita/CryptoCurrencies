package com.spitaliere.cryptocurrencies.ui.list.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.DialogSortListBinding

/**
 * Created by Rafael Spitaliere on 14/10/2019.
 **/
class SortDialogImpl : SortDialog {

    private var dialog : Dialog? = null

    override fun build(context: Context, sortByEnum: SortDialog.SortBy?, action: (SortDialog.SortBy) -> Unit): SortDialog {
        val binding: DialogSortListBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_sort_list, null, false)

        dialog = Dialog(context, R.style.CustomDialog)
        dialog?.window?.attributes?.gravity = Gravity.BOTTOM

        when(sortByEnum){
            SortDialog.SortBy.RANK -> { binding.byRank.isChecked = true }
            SortDialog.SortBy.NAME -> { binding.byName.isChecked = true }
            SortDialog.SortBy.PRICE -> { binding.byPrice.isChecked = true }
            SortDialog.SortBy.CHANGE -> { binding.byChange.isChecked = true }
            else -> {}
        }

        binding.byRank.setOnClickListener{ select(SortDialog.SortBy.RANK, action) }
        binding.byName.setOnClickListener{ select(SortDialog.SortBy.NAME, action) }
        binding.byPrice.setOnClickListener{ select(SortDialog.SortBy.PRICE, action) }
        binding.byChange.setOnClickListener{ select(SortDialog.SortBy.CHANGE, action) }

        dialog?.setContentView(binding.root)
        return this
    }

    private fun select(sortByEnum: SortDialog.SortBy, function: (SortDialog.SortBy) -> Unit){
        function(sortByEnum)
        close()
    }

    override fun show() {
        dialog?.show()
    }

    override fun close() {
        dialog?.dismiss()
        dialog = null
    }

}