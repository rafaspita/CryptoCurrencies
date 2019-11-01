package com.spitaliere.cryptocurrencies.ui.list

import android.content.Context
import androidx.core.content.ContextCompat
import com.spitaliere.cryptocurrencies.R
import com.spitaliere.cryptocurrencies.databinding.ItemCurrencyBinding
import com.spitaliere.cryptocurrencies.platform.base.BaseRecyclerViewAdapter
import com.spitaliere.domain.features.currency.entity.Currency
import com.squareup.picasso.Picasso

/**
 * Created by Rafael Spitaliere on 13/10/2019.
 **/
class ListCurrenciesAdapter(private val context: Context) : BaseRecyclerViewAdapter<Currency, ItemCurrencyBinding>(context) {

    override fun getLayout(): Int = R.layout.item_currency

    override fun areItemTheSame(oldItem: Currency, newItem: Currency): Boolean = true

    override fun areContentTheSame(oldItem: Currency, newItem: Currency): Boolean = true

    override fun prepareView(item: Currency, binding: ItemCurrencyBinding, position: Int) {
        binding.root.setOnClickListener{ clickItem?.let { click -> click(item, binding) } }

        Picasso.get()
                .load(context.getString(R.string.load_images_url_coincap, item.symbol.toLowerCase()))
                .into(binding.itemImage)


        with(binding.background){
            val color = if( item.changePercent24Hr < 0)  R.color.price_down else R.color.price_up
            setBackgroundColor(ContextCompat.getColor(context, color))
        }
    }
}