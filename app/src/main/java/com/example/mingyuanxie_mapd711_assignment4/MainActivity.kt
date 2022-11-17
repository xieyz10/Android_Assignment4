package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerViewModel
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductViewModel

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    lateinit var customerViewModel: CustomerViewModel
    lateinit var productViewModel: ProductViewModel
    lateinit var customerName: String
    lateinit var customerPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        initiateProductData()
    }

    fun initiateProductData(){
        //get content and viewModel
        context = this@MainActivity
        productViewModel.getProduct(context)!!.observe(this,Observer{
            if(it.isEmpty()){
                Toast.makeText( context,"insert product",Toast.LENGTH_LONG).show()
                productViewModel.insertProduct(context, "Apple", "Apple",
                    "Red", "32 GB", "$599")
                productViewModel.insertProduct(context, "SamSung", "SamSung",
                    "Silver", "64 GB", "$699")
                productViewModel.insertProduct(context, "Sony", "Sony",
                    "Blue", "128 GB", "$799")
            }
        })
    }

    fun btnLogin_pressed(view: View){
        if(view.id == R.id.btn_login_submit){
            customerName = findViewById<EditText>(R.id.editText_username).text.toString()
            customerPassword = findViewById<EditText>(R.id.editText_password).text.toString()

            customerViewModel.getCustomer(context, customerName,customerPassword)!!.observe(this, Observer {
                if (it == null) {
                    Toast.makeText( context,"Wrong username or password",Toast.LENGTH_LONG).show()
                }
                else {
                    //Toast.makeText( context,it.CustomerName+" "+ it.CustomerPassword,Toast.LENGTH_LONG).show()
                    val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString("customerId",it.CustomerId.toString())
                    editor.commit()
                    val intent = Intent(this@MainActivity, OrderActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    fun btnRegister_pressed(view: View){
        if(view.id == R.id.btn_navigateToRegisterPage){
            var intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}