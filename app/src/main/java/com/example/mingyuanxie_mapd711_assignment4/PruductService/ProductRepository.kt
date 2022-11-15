package com.example.mingyuanxie_mapd711_assignment4.PruductService

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerDatabase
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRepository {
    companion object {
        var productDatabase: ProductDatabase? = null
        var productModel: LiveData<ProductModel>? = null

        //initialize database
        fun initializeDB(context: Context): ProductDatabase {
            return ProductDatabase.getDataseClient(context)
        }

        //Initialize insertProduct()
        fun insertProduct(
            context: Context,
            phoneMake: String,
            phoneModel: String,
            phoneColor: String,
            storageCapacity: String,
            price: String,
        ) {
            productDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val productDetails = ProductModel(
                    phoneMake,
                    phoneModel,
                    phoneColor,
                    storageCapacity,
                    price,
                )
                productDatabase!!.productDao().insertProduct(productDetails)
            }
        }

        //Initialize getProducts()
        fun getProduct(context: Context) : LiveData<ProductModel>? {

            productDatabase = initializeDB(context)
            productModel = productDatabase!!.productDao().getProduct()
            return productModel
        }

    }
}