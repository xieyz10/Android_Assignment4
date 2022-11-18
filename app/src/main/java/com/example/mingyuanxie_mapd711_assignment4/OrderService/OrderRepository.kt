package com.example.mingyuanxie_mapd711_assignment4.OrderService

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderRepository {
    companion object {
        var orderDatabase: OrderDatabase? = null
        var orderModel: LiveData<List<OrderModel>>? = null

        //initialize database
        fun initializeDB(context: Context): OrderDatabase {
            return OrderDatabase.getDataseClient(context)
        }

        //Initialize insertProduct()
        fun insertOrder(
            context: Context,
            customerId: Int,
            productId: Int,
            orderDate: String,
            status: String,
        ) {
            orderDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val orderDetails = OrderModel(
                    customerId,
                    productId,
                    orderDate,
                    status,
                )
                orderDatabase!!.orderDao().insertOrder(orderDetails)
            }
        }

        //Initialize getOrder()
        fun getOrder(context: Context, customerId: Int) : LiveData<List<OrderModel>>? {

            orderDatabase = initializeDB(context)
            orderModel = orderDatabase!!.orderDao().getOrder(customerId)
            return orderModel
        }

        fun updateOrderStatus(context: Context, orderId:Int, status:String){
            orderDatabase = initializeDB(context)
            orderDatabase!!.orderDao().updateOrderStatus(orderId,status)
        }

    }
}