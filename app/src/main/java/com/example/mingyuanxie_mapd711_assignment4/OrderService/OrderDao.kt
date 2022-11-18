package com.example.mingyuanxie_mapd711_assignment4.OrderService

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {
    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrder(orderModel: OrderModel)

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM `order` where CustomerId = :customerId")
    fun getOrder(customerId:Int) : LiveData<List<OrderModel>>

    @Query("UPDATE `order` SET status = :status WHERE orderId =:orderId")
    fun updateOrderStatus(orderId:Int,status:String)
}