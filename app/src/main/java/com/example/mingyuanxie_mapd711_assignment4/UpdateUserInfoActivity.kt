package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.CustomerService.CustomerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateUserInfoActivity: AppCompatActivity()  {
    lateinit var context: Context
    lateinit var customerViewModel: CustomerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateuserinfo)
        context = this@UpdateUserInfoActivity
        customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        setupUserInfo()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.myOrder){
            var intent = Intent(this@UpdateUserInfoActivity, MyOrderActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.updateUserInfo){
            var intent = Intent(this@UpdateUserInfoActivity, UpdateUserInfoActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.product){
            var intent = Intent(this@UpdateUserInfoActivity, ProductListActivity::class.java)
            startActivity(intent)
        } else if(item.itemId == R.id.logout){
            var intent = Intent(this@UpdateUserInfoActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun btnRegister_submit_pressed(view: View){
        if(view.id == R.id.btn_updateUserInfo_submit){
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val customerId = sharedPref.getString("customerId", "")!!.toInt()
            var editText_userName = findViewById<EditText>(R.id.editText_userName).getText().toString()
            var editText_password = findViewById<EditText>(R.id.editText_password).getText().toString()
            var editText_firstName = findViewById<EditText>(R.id.editText_firstName).getText().toString()
            var editText_lastName = findViewById<EditText>(R.id.editText_lastName).getText().toString()
            var editText_address = findViewById<EditText>(R.id.editText_address).getText().toString()
            var editText_city = findViewById<EditText>(R.id.editText_city).getText().toString()
            var editText_country = findViewById<EditText>(R.id.editText_country).getText().toString()
            var editText_postalCode = findViewById<EditText>(R.id.editText_postalCode).getText().toString()

            CoroutineScope(Dispatchers.IO).launch {
                customerViewModel.updateCustomerInfo(context,customerId,editText_userName, editText_password,editText_firstName,editText_lastName,
                    editText_address,editText_city,editText_country,editText_postalCode)
            }
            Toast.makeText( context,"Succeed!", Toast.LENGTH_LONG).show()
        }
    }

    fun setupUserInfo(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val customerId = sharedPref.getString("customerId", "")!!.toInt()
        customerViewModel.getCustomerById(context,customerId)!!.observe(this, Observer {
            if (it!=null) {

                var editText_userName = findViewById<EditText>(R.id.editText_userName)
                var editText_password = findViewById<EditText>(R.id.editText_password)
                var editText_firstName = findViewById<EditText>(R.id.editText_firstName)
                var editText_lastName = findViewById<EditText>(R.id.editText_lastName)
                var editText_address = findViewById<EditText>(R.id.editText_address)
                var editText_city = findViewById<EditText>(R.id.editText_city)
                var editText_country = findViewById<EditText>(R.id.editText_country)
                var editText_postalCode = findViewById<EditText>(R.id.editText_postalCode)

                editText_userName.setText(it.UserName);
                editText_password.setText(it.Password)
                editText_firstName.setText(it.FirstName)
                editText_lastName.setText(it.LastName)
                editText_address.setText(it.Address)
                editText_city.setText(it.City)
                editText_country.setText(it.Country)
                editText_postalCode.setText(it.PostalCode)
            }
        })
    }

}