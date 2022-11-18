package com.example.mingyuanxie_mapd711_assignment4.CustomerService

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CustomerViewModel : ViewModel() {

    var liveDataCustomer: LiveData<CustomerModel>? = null

    fun insertCustomer(context: Context, userName: String, userPassword: String
    , customerFirstName:String, customerLastName:String, customerAddress: String, customerCity:String, customerPostalCode:String, customerCountry:String) {
        CustomerRepository.insertCustomer(
            context,
            userName,
            userPassword,
            customerFirstName,
            customerLastName,
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

    fun getCustomerById(context: Context, customerId: Int): LiveData<CustomerModel>?{
        liveDataCustomer = CustomerRepository.getCustomerById(context, customerId)
        return liveDataCustomer
    }

    fun updateCustomerInfo(context: Context, userId:Int, userName: String,password: String, firstName: String, lastName:String, address:String, city:String, country:String, postalCode: String){
        CustomerRepository.updateCustomerInfo(context, userId, userName, password,firstName,lastName, address, city, country, postalCode)
    }

}