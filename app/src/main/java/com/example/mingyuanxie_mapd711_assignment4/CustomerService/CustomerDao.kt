package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CustomerDao {
    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCustomer(customerModel: CustomerModel)

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM customer WHERE customerName =:customerName and customerPassword=:customerPassword")
    fun getCustomer(customerName: String, customerPassword:String) : LiveData<CustomerModel>

}