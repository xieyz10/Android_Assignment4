package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CustomerViewModel : ViewModel() {

    var liveDataCustomer: LiveData<CustomerModel>? = null

    fun insertCustomer(context: Context, customerName: String, customerPassword: String
    , customerAddress: String, customerCity:String, customerPostalCode:String, customerCountry:String) {
        CustomerRepository.insertCustomer(
            context,
            customerName,
            customerPassword,
            customerAddress,
            customerCity,
            customerPostalCode,
            customerCountry
        )
    }

    fun getCustomer(context: Context, customerName: String, customerPassword: String): LiveData<CustomerModel>?{
        liveDataCustomer = CustomerRepository.getCustomer(context, customerName, customerPassword)
        return liveDataCustomer
    }

}