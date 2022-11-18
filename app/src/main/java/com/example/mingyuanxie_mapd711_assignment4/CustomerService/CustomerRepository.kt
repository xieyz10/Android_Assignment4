package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mingyuanxie_mapd711_assignment4.OrderService.OrderRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerRepository {

    companion object {
        var customerDatabase: CustomerDatabase? = null
        var customerModel: LiveData<CustomerModel>? = null

        //initialize database
        fun initializeDB(context: Context): CustomerDatabase {
            return CustomerDatabase.getDataseClient(context)
        }

        //Initialize insertStudent()
        fun insertCustomer(
            context: Context,
            userName: String,
            password: String,
            firstName:String,
            lastName:String,
            adress: String,
            city: String,
            postalCode: String,
            country: String
        ) {
            customerDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val customerDetails = CustomerModel(
                    userName,
                    password,
                    firstName,
                    lastName,
                    adress,
                    city,
                    postalCode,
                    country
                )
                customerDatabase!!.customerDao().insertCustomer(customerDetails)
            }
        }

        //Initialize getCustomers()
        fun getCustomer(context: Context, customerName: String, customerPassword: String) : LiveData<CustomerModel>? {

            customerDatabase = initializeDB(context)
            customerModel = customerDatabase!!.customerDao().getCustomer(customerName, customerPassword)
            return customerModel
        }

        fun updateCustomerInfo(context: Context, userId:Int,userName: String, password: String, firstName: String, lastName:String, address:String, city:String, country: String, postalCode: String){
            customerDatabase = initializeDB(context)
            customerDatabase!!.customerDao().updateUserInfo(userId,userName, password,firstName,lastName, address, city, country, postalCode)
        }

        fun getCustomerById(context: Context, customerId:Int): LiveData<CustomerModel>?{
            customerDatabase = initializeDB(context)
            customerModel = customerDatabase!!.customerDao().getCustomerById(customerId)
            return customerModel
        }


    }
}