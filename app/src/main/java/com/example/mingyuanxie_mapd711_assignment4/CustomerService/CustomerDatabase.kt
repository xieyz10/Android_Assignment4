package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CustomerModel::class), version = 1, exportSchema = false)
abstract class CustomerDatabase: RoomDatabase()  {
    abstract fun customerDao() : CustomerDao

    //companion object means an object declaration inside a class
    companion object {
        //Volatile object or property is immediately made visible to other threads.
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDataseClient(context: Context) : CustomerDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, CustomerDatabase::class.java, "CustomerDB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }
}