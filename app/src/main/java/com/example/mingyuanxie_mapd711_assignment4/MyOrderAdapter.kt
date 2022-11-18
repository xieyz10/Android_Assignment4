package com.example.mingyuanxie_mapd711_assignment4

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyOrderAdapter(private val context: Activity, private val arrayList:
ArrayList<MyOrder>): ArrayAdapter<MyOrder>(context,R.layout.myorder_list_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.myorder_list_item,null)

        val imageView = view.findViewById<ImageView>(R.id.imageView_phone)
        val orderDate = view.findViewById<TextView>(R.id.textview_orderDate)
        val status = view.findViewById<TextView>(R.id.textview_status)

        imageView.setImageResource(arrayList[position].ImageId)
        orderDate.text = arrayList[position].orderDate
        status.text = arrayList[position].status

        return view
    }
}