package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListCartItemBinding
import com.example.myapplication.databinding.ListItemBinding
import com.example.myapplication.model.ProductList
import com.techmah.mapandretrofit.adapters.ListCartViewHolder
import com.techmah.mapandretrofit.adapters.ListViewHolder
import java.util.Collections.emptyList


class CartListAdapter(val listItem: List<ProductList>, private val listener: AdapterClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ListCartViewHolder(
            ListCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListCartViewHolder).bind(listItem[position])
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

}