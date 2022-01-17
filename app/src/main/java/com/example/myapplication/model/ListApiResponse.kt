package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ApiListResponse(

    @SerializedName("products") val products: List<ProductList>
)

@Entity
data class ProductList(

    @SerializedName("name") val name: String,
    @PrimaryKey
    @SerializedName("product_id") val productId: Int,
    @SerializedName("image") val image: String,
    @SerializedName("thumb") val thumbnail: String,
    @SerializedName("price") val price: String,
     var count: Int = 0
)