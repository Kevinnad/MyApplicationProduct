package com.techmah.mapandretrofit.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.AdapterClickListener
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.ProductList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class ListViewHolder(
    itemView: ListItemBinding,
    private val listener: AdapterClickListener
) : RecyclerView.ViewHolder(itemView.root) {

    fun bind(apiListResponse: ProductList) {

        itemView.tv_name.text = apiListResponse.name
        itemView.tv_price_id.text = apiListResponse.price
        Picasso.get().load(apiListResponse.image).into(itemView.iv_product_image)

        if (apiListResponse.count > 0){
            itemView.tv_count.text = apiListResponse.count.toString()
            addProductItem()
        }else{
            defaultProductItem()
        }

        itemView.bt_add.setOnClickListener({
            listener.onItemPlus(apiListResponse.productId)
        })
        itemView.bt_plus.setOnClickListener({
            listener.onItemPlus(apiListResponse.productId)
        })
        itemView.bt_minus.setOnClickListener({
            listener.onItemMinus(apiListResponse.productId)
        })
    }

    private fun addProductItem(){

        itemView.bt_add.visibility = View.GONE

        itemView.bt_minus.visibility = View.VISIBLE
        itemView.bt_plus.visibility = View.VISIBLE
        itemView.tv_count.visibility = View.VISIBLE
    }

    private fun defaultProductItem(){

        itemView.bt_add.visibility = View.VISIBLE

        itemView.bt_minus.visibility = View.GONE
        itemView.bt_plus.visibility = View.GONE
        itemView.tv_count.visibility = View.GONE
    }

}