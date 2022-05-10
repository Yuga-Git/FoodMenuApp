package com.example.khadyayatra.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.khadyayatra.R
import com.example.khadyayatra.model.Restaurants

class DashboardRecyclerAdapter(val context: Context, val itemList: ArrayList<Restaurants>): RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>(){

    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtRestName : TextView = view.findViewById(R.id.txtRestName)
        val txtRestRating : TextView = view.findViewById(R.id.txtRestRating)
        val txtRestPrice : TextView = view.findViewById(R.id.txtRestPrice)
        //val imgRestImage : ImageView = view.findViewById(R.id.imgRestImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_dashboard_single_row, parent,false)

        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val restaurants = itemList[position]
        holder.txtRestName.text = restaurants.restName
        holder.txtRestRating.text = restaurants.restRating
        holder.txtRestPrice.text = restaurants.pricePerPerson
       // holder.imgRestImage.setImageResource(restaurants.restImage)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}