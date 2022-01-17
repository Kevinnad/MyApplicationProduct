package com.example.myapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.adapters.AdapterClickListener
import com.example.myapplication.adapters.CartListAdapter
import com.example.myapplication.databinding.FragmentCartBinding
import com.example.myapplication.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_item.*

@AndroidEntryPoint
class ProductCartFragment : Fragment(R.layout.fragment_cart) {

    private val cardViewModel: CartViewModel by activityViewModels()
    lateinit var cartBinding: FragmentCartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        cardViewModel.getCartList()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        cartBinding = DataBindingUtil.inflate<FragmentCartBinding>(
            inflater,
            R.layout.fragment_cart,
            container,
            false
        )

        return cartBinding.root
    }

    fun observer() {

        cardViewModel.cartListData.observe(requireActivity()) {

            cartBinding.rvList.adapter = CartListAdapter(it, object : AdapterClickListener {
                override fun onItemPlus(productID: Int) {
                    cardViewModel.onCartProduct(productID,true)
                }

                override fun onItemMinus(productID: Int) {
                    cardViewModel.onCartProduct(productID,false)
                }

            })
        }

    }
}