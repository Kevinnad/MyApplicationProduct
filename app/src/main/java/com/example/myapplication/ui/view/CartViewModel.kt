package com.example.myapplication.ui.view

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ApiListResponse
import com.example.myapplication.model.ProductList
import com.example.myapplication.network.ResultWrapper
import com.example.myapplication.repositories.ProductRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CartViewModel @ViewModelInject constructor(private val productRepo: ProductRepo) : ViewModel() {

    val cartListData = MutableLiveData<List<ProductList>>()


    fun onCartProduct(productId : Int, isInc : Boolean){

        viewModelScope.launch(Dispatchers.Default) {
            val isSuccess = productRepo.setProductInCart(productId,isInc)
            if(isSuccess) getCartList()
        }

    }

    fun getCartList(){

        viewModelScope.launch(Dispatchers.Default) {
            cartListData.postValue(productRepo.fetchCartList())

        }

    }
}