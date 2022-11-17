package com.example.mingyuanxie_mapd711_assignment4

import android.app.Activity
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductAdapter(private val context: Activity, private val arrayList:
    ArrayList<Product>):ArrayAdapter<Product>(context,R.layout.list_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.list_item,null)

        val imageView = view.findViewById<ImageView>(R.id.imageView_phone)
        val phoneModel = view.findViewById<TextView>(R.id.phoneModel)
        val phonePrice = view.findViewById<TextView>(R.id.phonePrice)

        imageView.setImageResource(arrayList[position].ImageId)
        phoneModel.text = arrayList[position].phoneModel
        phonePrice.text = arrayList[position].price

        return view
    }
}