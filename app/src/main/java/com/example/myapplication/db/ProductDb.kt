package com.example.myapplication.db

import androidx.room.Room
import com.example.myapplication.model.ProductList
import javax.inject.Inject

class ProductDb @Inject constructor(val appDataBase: AppDataBase){

    suspend fun insertProductList(productList: List<ProductList>){
        appDataBase.productDao().insertAll(productList)
    }

    suspend fun insertProduct(productList: ProductList){
        appDataBase.productDao().insertProduct(productList)
    }

    suspend fun getProductlist() : List<ProductList>?{
        return appDataBase.productDao().getProductList()
    }

    suspend fun getProductByID(productId : Int) : ProductList?{
        return appDataBase.productDao().getProductById(productId)
    }
}