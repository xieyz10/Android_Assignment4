package com.example.mingyuanxie_mapd711_assignment4.OrderService

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
class OrderModel(
    @ColumnInfo(name = "customerId")
    var CustomerId: Int,
    @ColumnInfo(name = "productId")
    var ProductId: Int,
    @ColumnInfo(name = "orderDate")
    var OrderDate: String,
    @ColumnInfo(name = "status")
    var Status: String,
) {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    var OrderId: Int? = null
}