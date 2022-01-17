package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.ProductList

@Database(entities = [ProductList::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun productDao() : ProductDao
}