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


class HomeViewModel @ViewModelInject constructor(private val productRepo: ProductRepo) : ViewModel() {

    val listResponseData = MutableLiveData<ApiListResponse>()
    val productListData = MutableLiveData<List<ProductList>>()
    val errorValue = MutableLiveData<String>()

    fun fetchList(){

        viewModelScope.launch(Dispatchers.Default) {

            val listResponse = productRepo.fetchProductList()

            when(listResponse){

                is ResultWrapper.Success ->{

                    productRepo.setProductList(listResponse.value.products)

                    listResponseData.postValue(listResponse.value)
                }
                is ResultWrapper.NetworkError -> {
                    errorValue.postValue(listResponse.value)
                }
                is ResultWrapper.GenericError -> {
                    errorValue.postValue(listResponse.value)
                }
            }
        }


    }

    fun onCartProduct(productId : Int, isInc : Boolean){

        viewModelScope.launch(Dispatchers.Default) {
           val isSuccess = productRepo.setProductInCart(productId,isInc)
            if(isSuccess) getProductList()
        }

    }

    fun getProductList(){

        viewModelScope.launch(Dispatchers.Default) {
            val productList = productRepo.fetchCartListFromDB()
            if(productList!= null && productList.size > 0){
                productListData.postValue(productList)
            }else{
                fetchList()
            }
        }

    }
}