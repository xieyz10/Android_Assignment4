package com.example.mingyuanxie_mapd711_assignment4.PruductService

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
class ProductModel (
    @ColumnInfo(name = "phoneMake")
    var PhoneMake: String,
    @ColumnInfo(name = "phoneModel")
    var PhoneModel: String,
    @ColumnInfo(name = "phoneColor")
    var PhoneColor: String,
    @ColumnInfo(name = "storageCapacity")
    var StorageCapacity: String,
    @ColumnInfo(name = "price")
    var Price: String,
) {
    //defining a primary key field Id
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    var ProductId: Int? = null
}