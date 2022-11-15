package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerModel(
    @ColumnInfo(name = "customerName")
    var CustomerName: String,
    @ColumnInfo(name = "customerPassword")
    var CustomerPassword: String,
    @ColumnInfo(name = "customerAddress")
    var CustomerAddress: String,
    @ColumnInfo(name = "customerCity")
    var CustomerCity: String,
    @ColumnInfo(name = "customerPostalCode")
    var CustomerPostalCode: String,
    @ColumnInfo(name = "customerCountry")
    var CustomerCountry: String,
) {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "customerId")
    var CustomerId: Int? = null
}