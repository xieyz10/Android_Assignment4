package com.example.mingyuanxie_mapd711_assignment4.PruductService

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ProductViewModel: ViewModel() {
    var liveDataProduct: LiveData<List<ProductModel>>? = null
    var product: LiveData<ProductModel>? = null
    fun insertProduct(context: Context, phoneMake: String, phoneModel: String
                       , phoneColor: String, storageCapacity:String, price:String) {
        ProductRepository.insertProduct(
            context,
            phoneMake,
            phoneModel,
            phoneColor,
            storageCapacity,
            price,
        )
    }

    fun getProduct(context: Context): LiveData<List<ProductModel>>?{
        liveDataProduct = ProductRepository.getProduct(context)
        return liveDataProduct
    }

    fun getProductById(context: Context, productId:Int):LiveData<ProductModel>?{
        product = ProductRepository.getProductById(context,productId)
        return product
    }
}