package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.OrderService.OrderViewModel
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductModel
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductViewModel
import java.time.LocalDateTime

class ProductDetailsActivity: AppCompatActivity() {
    lateinit var productViewModel: ProductViewModel
    lateinit var orderViewModel: OrderViewModel
    lateinit var context: Context
    lateinit var orderedProduct: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productdetails)
        context = this@ProductDetailsActivity
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        setUpProductDetail()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.myOrder){
            var intent = Intent(this@ProductDetailsActivity, MyOrderActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.updateUserInfo){
            var intent = Intent(this@ProductDetailsActivity, UpdateUserInfo::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.product){
            var intent = Intent(this@ProductDetailsActivity, ProductListActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun setUpProductDetail(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val productId = sharedPref.getString("productId", "")
        productViewModel.getProductById(context, productId!!.toInt())!!.observe(this, Observer{
            if(it != null){
                orderedProduct = it
                if(it.ProductId == 1){
                    findViewById<ImageView>(R.id.imageView_phone).setImageResource(R.drawable.iphone)
                }else if(it.ProductId == 2){
                    findViewById<ImageView>(R.id.imageView_phone).setImageResource(R.drawable.samsung)
                }else if(it.ProductId == 3){
                    findViewById<ImageView>(R.id.imageView_phone).setImageResource(R.drawable.sony)
                }
                findViewById<TextView>(R.id.textView_phoneMake).text = it.PhoneMake
                findViewById<TextView>(R.id.textView_phoneModel).text = it.PhoneModel
                findViewById<TextView>(R.id.textView_phoneColor).text = it.PhoneColor
                findViewById<TextView>(R.id.textView_price).text = it.Price
                findViewById<TextView>(R.id.textView_storageCapacity).text = it.StorageCapacity
            }
        })
    }

    fun btnPlaceOrder_pressed(view:View){
        if(view.id == R.id.btn_placeOrder){
            context = this@ProductDetailsActivity
            if(orderedProduct!=null){
                val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
                val customerId = sharedPref.getString("customerId", "")!!.toInt()
                val productId = orderedProduct.ProductId
                val orderDate = LocalDateTime.now().toString()
                val status = "Placed"
                orderViewModel.insertOrder(context, customerId, productId!!, orderDate, status)
                Toast.makeText( context,"Success!",Toast.LENGTH_LONG).show()
            }
        }
    }
}