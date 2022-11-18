package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.OrderService.OrderViewModel
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductViewModel
import com.example.mingyuanxie_mapd711_assignment4.databinding.ActivityMyorderBinding

class MyOrderActivity: AppCompatActivity()  {
    lateinit var orderViewModel: OrderViewModel
    lateinit var productViewModel: ProductViewModel
    lateinit var context: Context
    private lateinit var binding: ActivityMyorderBinding
    lateinit var imageIdArray: Array<Int>
    lateinit var orderDateArray: Array<String>
    lateinit var statusArray: Array<String>
    lateinit var productIdArray:Array<Int>
    lateinit var orderIdArray:Array<Int>
    lateinit var myOrderArrayList: ArrayList<MyOrder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyorderBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_myorder)
        context = this@MyOrderActivity
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        setupOrderList()
        setupListItemClickEvent()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.myOrder){
            var intent = Intent(this@MyOrderActivity, MyOrderActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.updateUserInfo){
            var intent = Intent(this@MyOrderActivity, UpdateUserInfoActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.product){
            var intent = Intent(this@MyOrderActivity, ProductListActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.logout){
            var intent = Intent(this@MyOrderActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun setupOrderList(){
        val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        val customerId = sharedPref.getString("customerId", "")
        orderViewModel.getOrder(context,customerId!!.toInt())!!.observe(this, Observer{
            if(it.size != 0){
                imageIdArray = Array(it.size){0}
                orderDateArray = Array(it.size){""}
                statusArray = Array(it.size){""}
                productIdArray = Array(it.size){0}
                myOrderArrayList = ArrayList()
                orderIdArray = Array(it.size){0}
                for( i in 0 until it.size) {
                    if (it.get(i).ProductId == 1) {
                        imageIdArray[i] = R.drawable.iphone
                    } else if (it.get(i).ProductId == 2) {
                        imageIdArray[i] = R.drawable.samsung
                    } else if (it.get(i).ProductId == 3) {
                        imageIdArray[i] = R.drawable.sony
                    }
                    orderDateArray[i] = it.get(i).OrderDate
                    statusArray[i] = it.get(i).Status
                    productIdArray[i] = it.get(i).ProductId!!.toInt()
                    orderIdArray[i] = it.get(i).OrderId!!.toInt()
                }
                myOrderArrayList = ArrayList()
                for(i in statusArray.indices){
                    val myOrder = MyOrder(orderIdArray[i],orderDateArray[i],statusArray[i],imageIdArray[i],productIdArray[i])
                    myOrderArrayList.add(myOrder)
                }

                binding.orderListView.isClickable = true
                val arrayAdapter = MyOrderAdapter(this, myOrderArrayList)
                var mListView = findViewById<ListView>(R.id.orderListView)
                mListView.adapter = arrayAdapter
            }
        })
    }

    fun setupListItemClickEvent(){
        var mListView = findViewById<ListView>(R.id.orderListView)
        val intent = Intent(this@MyOrderActivity, OrderDetailsActivity::class.java)
        mListView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString("productId",productIdArray[position].toString())
            editor.putString("orderId",orderIdArray[position].toString())
            editor.commit()
            startActivity(intent)
        })
    }
}