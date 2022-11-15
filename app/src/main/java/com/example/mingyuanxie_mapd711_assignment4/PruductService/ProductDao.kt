package com.example.mingyuanxie_mapd711_assignment4.PruductService

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerModel

@Dao
interface ProductDao {
    //defining an insert method using @Insert Annotation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(productModel: ProductModel)

    //defining a query method using @Query Annotation
    @Query("SELECT * FROM product")
    fun getProduct() : LiveData<ProductModel>
}