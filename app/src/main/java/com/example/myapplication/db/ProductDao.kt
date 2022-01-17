package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.model.ProductList

@Dao
interface ProductDao {

    @Query("SELECT * FROM productlist")
    suspend fun getProductList() : List<ProductList>?

    @Query("SELECT * FROM productlist WHERE productID=:productID")
    suspend fun getProductById(productID : Int) : ProductList?

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(productList : List<ProductList>)

    @Insert(onConflict = REPLACE)
    suspend fun insertProduct(productList : ProductList)

    @Delete
    suspend fun delete(product: ProductList)


}