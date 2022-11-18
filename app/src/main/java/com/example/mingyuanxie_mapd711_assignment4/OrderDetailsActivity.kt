package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.OrderService.OrderViewModel
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderDetailsActivity: AppCompatActivity()  {
    lateinit var orderViewModel: OrderViewModel
    lateinit var productViewModel: ProductViewModel
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orderdetails)
        context = this@OrderDetailsActivity
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        findViewById<Button>(R.id.btn_cancelOrder).setBackgroundColor(Color.RED)
        setUpOrderDetail()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.myOrder){
            var intent = Intent(this@OrderDetailsActivity, MyOrderActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.updateUserInfo){
            var intent = Intent(this@OrderDetailsActivity, UpdateUserInfoActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.product){
            var intent = Intent(this@OrderDetailsActivity, ProductListActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.logout){
            var intent = Intent(this@OrderDetailsActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun setUpOrderDetail(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val productId = sharedPref.getString("productId", "")
        productViewModel.getProductById(context, productId!!.toInt())!!.observe(this, Observer{
            if(it != null){
                //orderedProduct = it
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

    fun btn_deliveredOrder_pressed(view: View) {
        if (view.id == R.id.btn_deliveredOrder) {
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val orderId = sharedPref.getString("orderId", "")!!.toInt()
            CoroutineScope(Dispatchers.IO).launch {
                orderViewModel.updateOrderStatus(context,orderId,"Delivered")
            }
            Toast.makeText( context,"Your order has been delivered ", Toast.LENGTH_LONG).show()
        }
    }

    fun btn_cancelOrder_pressed(view: View) {
        if (view.id == R.id.btn_cancelOrder) {
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val orderId = sharedPref.getString("orderId", "")!!.toInt()
            CoroutineScope(Dispatchers.IO).launch {
                orderViewModel.updateOrderStatus(context,orderId,"Canceled")
            }
            Toast.makeText( context,"Your order has been canceled", Toast.LENGTH_LONG).show()
        }
    }
}