package com.example.mingyuanxie_mapd711_assignment4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mingyuanxie_mapd711_assignment4.PruductService.ProductViewModel
import com.example.mingyuanxie_mapd711_assignment4.databinding.ActivityProductlistBinding


class ProductListActivity: AppCompatActivity()  {
    lateinit var productViewModel: ProductViewModel
    lateinit var context: Context
    private lateinit var binding: ActivityProductlistBinding
    lateinit var productIdArray: Array<Int>
    lateinit var imageIdArray: Array<Int>
    lateinit var phoneModelArray: Array<String>
    lateinit var phonePriceArray: Array<String>
    lateinit var productArrayList: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductlistBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_productlist)
        
        context = this@ProductListActivity
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        setupPhoneList()
        setupListItemClickEvent()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.myOrder){
            var intent = Intent(this@ProductListActivity, MyOrderActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.updateUserInfo){
            var intent = Intent(this@ProductListActivity, UpdateUserInfoActivity::class.java)
            startActivity(intent)
        }else if(item.itemId == R.id.product){
            var intent = Intent(this@ProductListActivity, ProductListActivity::class.java)
            startActivity(intent)
        } else if(item.itemId == R.id.logout){
            var intent = Intent(this@ProductListActivity, MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun setupPhoneList(){
        productViewModel.getProduct((context))!!.observe(this, Observer{
            if(it.size != 0){
                imageIdArray = Array(it.size){0}
                phoneModelArray = Array(it.size){""}
                phonePriceArray = Array(it.size){""}
                productIdArray = Array(it.size){0}
                for( i in 0 until it.size) {
                    if (it.get(i).ProductId == 1) {
                        imageIdArray[i] = R.drawable.iphone
                    } else if (it.get(i).ProductId == 2) {
                        imageIdArray[i] = R.drawable.samsung
                    } else if (it.get(i).ProductId == 3) {
                        imageIdArray[i] = R.drawable.sony
                    }
                    phoneModelArray[i] = it.get(i).PhoneModel
                    phonePriceArray[i] = it.get(i).Price
                    productIdArray[i] = it.get(i).ProductId!!.toInt()
                }
                productArrayList = ArrayList()
                for(i in phoneModelArray.indices){
                    val product = Product(phoneModelArray[i],phonePriceArray[i],imageIdArray[i],productIdArray[i])
                    productArrayList.add(product)
                }
                binding.phoneModelListView.isClickable = true
                val arrayAdapter = ProductAdapter(this, productArrayList)
                var mListView = findViewById<ListView>(R.id.phoneModelListView)
                mListView.adapter = arrayAdapter
            }
        })
    }

    fun setupListItemClickEvent(){
        var mListView = findViewById<ListView>(R.id.phoneModelListView)
        val intent = Intent(this@ProductListActivity, ProductDetailsActivity::class.java)
        mListView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val sharedPref: SharedPreferences = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPref.edit()

            editor.putString("productId",productIdArray[position].toString())
            editor.commit()
            startActivity(intent)
        })
    }

}