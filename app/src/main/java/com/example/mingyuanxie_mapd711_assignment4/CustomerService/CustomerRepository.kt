package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import android.content.Context
import androidx.lifecycle.LiveData
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
            customerName: String,
            customerPassword: String,
            customerAddress: String,
            customerCity: String,
            customerPostalCode: String,
            customerCountry: String
        ) {
            customerDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val customerDetails = CustomerModel(
                    customerName,
                    customerPassword,
                    customerAddress,
                    customerCity,
                    customerPostalCode,
                    customerCountry
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

    }
}