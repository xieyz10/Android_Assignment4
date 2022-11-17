package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerViewModel

class RegisterActivity : AppCompatActivity()  {
    lateinit var context: Context
    lateinit var customerViewModel: CustomerViewModel

    var editText_customerName = ""
    var editText_passWord = ""
    var editText_address = ""
    var editText_city = ""
    var editText_postalCode = ""
    var editText_country = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun btnRegister_submit_pressed(view: View){
        if(view.id == R.id.btn_register_submit){
            //get content and viewModel
            context = this@RegisterActivity
            customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
            //get content from editText
            editText_customerName = findViewById<EditText>(R.id.editText_customerName).text.toString()
            editText_passWord = findViewById<EditText>(R.id.editText_password).text.toString()
            editText_address = findViewById<EditText>(R.id.editText_address).text.toString()
            editText_city = findViewById<EditText>(R.id.editText_city).text.toString()
            editText_postalCode = findViewById<EditText>(R.id.editText_postalCode).text.toString()
            editText_country = findViewById<EditText>(R.id.editText_country).text.toString()

            if (editText_customerName.isEmpty()) {
                Toast.makeText( context,"Customer Name should not be empty", Toast.LENGTH_LONG).show()
            }
            else if (editText_passWord.isEmpty()) {
                Toast.makeText( context,"Customer password should not be empty", Toast.LENGTH_LONG).show()            }
            else {
                customerViewModel.insertCustomer(context, editText_customerName, editText_passWord,
                    editText_address, editText_city, editText_postalCode, editText_country)
                Toast.makeText( context,"Succeed!", Toast.LENGTH_LONG).show()
            }
        }
    }
}