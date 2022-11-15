package com.example.mingyuanxie_mapd711_assignment4.PruductService

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerDao

@Database(entities = arrayOf(ProductModel::class), version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase()  {
    abstract fun productDao() : ProductDao
    //companion object means an object declaration inside a class
    companion object {
        //Volatile object or property is immediately made visible to other threads.
        @Volatile
        private var INSTANCE:ProductDatabase? = null

        fun getDataseClient(context: Context) : ProductDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, ProductDatabase::class.java, "ProductDB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}