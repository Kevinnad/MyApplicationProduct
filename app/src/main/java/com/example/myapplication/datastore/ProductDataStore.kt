package com.example.myapplication.datastore

import com.example.myapplication.model.ApiListResponse
import com.example.myapplication.model.ProductList
import com.example.myapplication.network.ResultWrapper

interface ProductDataStore {

    suspend fun getProductList() : ResultWrapper<ApiListResponse>

    suspend fun getCartList(): List<ProductList>

    suspend fun getProductListFromDB(): List<ProductList>?

    suspend fun setProductInCart(productID : Int, isInc : Boolean) : Boolean

    suspend fun setProductList(productList : List<ProductList>)
}