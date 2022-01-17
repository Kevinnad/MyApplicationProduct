package com.example.myapplication.datastore

import com.example.myapplication.db.ProductDb
import com.example.myapplication.model.ApiListResponse
import com.example.myapplication.model.ProductList
import com.example.myapplication.network.ResultWrapper
import com.example.myapplication.network.Services
import com.example.myapplication.network.handleRequest
import java.util.*
import javax.inject.Inject

class ProductDataStoreImpl @Inject constructor(val services: Services, val productDb: ProductDb): ProductDataStore {


    override suspend fun getProductList(): ResultWrapper<ApiListResponse> {
        return handleRequest { services.getProductListApi() }
    }

    override suspend fun getCartList() : List<ProductList> {

        val productList = ArrayList<ProductList>()

        val productListDb = productDb.getProductlist()

        if(productListDb != null){

            for(product in productListDb){
                if(product.count > 0){
                    productList.add(product)
                }
            }
        }

        return productList
    }

    override suspend fun getProductListFromDB(): List<ProductList>? {
        return productDb.getProductlist()
    }

    override suspend fun setProductInCart(productID: Int, isInc: Boolean) : Boolean {

        val product = productDb.getProductByID(productID)

        if(product != null){
            calculateCartCount(product,isInc)
            productDb.insertProduct(product)
            return true
        }
        return false
    }

    override suspend fun setProductList(productList: List<ProductList>) {
        productDb.insertProductList(productList)
    }

    private fun calculateCartCount(productList: ProductList, isInc: Boolean){

        if(isInc){
            productList.count = productList.count + 1
        }else{
            productList.count = productList.count - 1
        }
    }

}