package com.example.myapplication.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.adapters.AdapterClickListener
import com.example.myapplication.adapters.ProductListAdapter
import com.example.myapplication.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_product) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    lateinit var productBinding : FragmentProductBinding

    private val listAdapter by lazy {
        ProductListAdapter(object : AdapterClickListener {
            override fun onItemPlus(productID: Int) {
                homeViewModel.onCartProduct(productID,true)
            }

            override fun onItemMinus(productID: Int) {
                homeViewModel.onCartProduct(productID,false)
            }

        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productBinding = DataBindingUtil.inflate<FragmentProductBinding>(inflater,R.layout.fragment_product,container,false)

        return productBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callProductApi()
        oberservers()

        productBinding.rvList.adapter = listAdapter

        productBinding.ivGoCart.setOnClickListener({

            NavHostFragment.findNavController(this@HomeFragment)
                .navigate(R.id.action_details_fragment)
        })

    }

    private fun callProductApi(){
        homeViewModel.getProductList()
    }

    private fun oberservers(){

        homeViewModel.listResponseData.observe(requireActivity(), Observer {

            listAdapter.setDataList(it.products)
        })

        homeViewModel.productListData.observe(requireActivity(), Observer {

            listAdapter.setDataList(it)
        })

        homeViewModel.errorValue.observe(requireActivity(), Observer {

            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })
    }

}