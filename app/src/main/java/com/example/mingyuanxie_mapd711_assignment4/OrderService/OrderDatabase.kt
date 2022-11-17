package com.example.mingyuanxie_mapd711_assignment4.OrderService

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductModel

@Database(entities = arrayOf(OrderModel::class), version = 1, exportSchema = false)
abstract class OrderDatabase: RoomDatabase()  {
    abstract fun orderDao() : OrderDao
    //companion object means an object declaration inside a class
    companion object {
        //Volatile object or property is immediately made visible to other threads.
        @Volatile
        private var INSTANCE: OrderDatabase? = null

        fun getDataseClient(context: Context) : OrderDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, OrderDatabase::class.java, "OrderDB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}