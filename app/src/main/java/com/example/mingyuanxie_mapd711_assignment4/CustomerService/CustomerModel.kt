package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerModel(
    @ColumnInfo(name = "userName")
    var UserName: String,
    @ColumnInfo(name = "password")
    var Password: String,
    @ColumnInfo(name = "firstName")
    var FirstName: String,
    @ColumnInfo(name = "lastName")
    var LastName: String,
    @ColumnInfo(name = "address")
    var Address: String,
    @ColumnInfo(name = "city")
    var City: String,
    @ColumnInfo(name = "postalCode")
    var PostalCode: String,
    @ColumnInfo(name = "country")
    var Country: String,
) {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    var UserId: Int? = null
}