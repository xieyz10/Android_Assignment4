package com.example.mingyuanxie_mapd711_assignment4.OrderService

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class OrderViewModel: ViewModel() {
    var liveDataOrder: LiveData<List<OrderModel>>? = null

    fun insertOrder(context: Context, customerId: Int, productId: Int
                      , orderDate: String, status:String) {
        OrderRepository.insertOrder(
            context,
            customerId,
            productId,
            orderDate,
            status
        )
    }

    fun getOrder(context: Context): LiveData<List<OrderModel>>?{
        liveDataOrder = OrderRepository.getOrder(context)
        return liveDataOrder
    }
}