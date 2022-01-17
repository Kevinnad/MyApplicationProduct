package com.example.myapplication.repositories

import com.example.myapplication.datastore.ProductDataStore
import com.example.myapplication.model.ProductList
import com.example.myapplication.network.Services
import com.example.myapplication.network.handleRequest
import javax.inject.Inject

class ProductRepo @Inject constructor(private val productDataStore: ProductDataStore) {

    suspend fun fetchProductList() = productDataStore.getProductList()

    suspend fun fetchCartList() = productDataStore.getCartList()

    suspend fun fetchCartListFromDB() = productDataStore.getProductListFromDB()

    suspend fun setProductInCart(productId : Int, isInc : Boolean) = productDataStore.setProductInCart(productId,isInc)

    suspend fun setProductList(productList : List<ProductList>) = productDataStore.setProductList(productList)


}