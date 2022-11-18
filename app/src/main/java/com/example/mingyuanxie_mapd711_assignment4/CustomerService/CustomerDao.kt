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
    @Query("SELECT * FROM customer WHERE userName =:userName and password=:password")
    fun getCustomer(userName: String, password:String) : LiveData<CustomerModel>

    @Query("SELECT * FROM customer WHERE userId =:userId")
    fun getCustomerById(userId: Int) : LiveData<CustomerModel>

    @Query("UPDATE customer SET userName=:userName, password=:password, firstName=:firstName, lastName=:lastName, address=:address, city=:city, country=:country, postalCode=:postalCode WHERE userId =:userId")
    fun updateUserInfo(userId:Int,userName:String, password: String, firstName:String, lastName:String, address:String, city:String, country:String, postalCode:String)
}