package com.techmah.mapandretrofit.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.AdapterClickListener
import com.example.myapplication.databinding.ListCartItemBinding
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.ProductList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class ListCartViewHolder(
    itemView: ListCartItemBinding,
    private val listener: AdapterClickListener
) : RecyclerView.ViewHolder(itemView.root) {

    fun bind(apiListResponse: ProductList) {

        itemView.tv_name.text = apiListResponse.name
        itemView.tv_price_id.text = apiListResponse.price
        Picasso.get().load(apiListResponse.image).into(itemView.iv_product_image)

        itemView.tv_count.text = apiListResponse.count.toString()

        itemView.bt_plus.setOnClickListener({
            listener.onItemPlus(apiListResponse.productId)
        })
        itemView.bt_minus.setOnClickListener({
            listener.onItemMinus(apiListResponse.productId)
        })

    }


}