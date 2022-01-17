package com.example.myapplication.db

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DbSource @Inject constructor(val context: Context) {

    fun createDB() : AppDataBase{

        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "product-database"
        ).build()
    }
}