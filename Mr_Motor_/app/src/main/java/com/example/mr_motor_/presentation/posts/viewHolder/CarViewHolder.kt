package com.example.mr_motor_.presentation.posts.viewHolder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class CarViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val firstName : TextView = itemView.findViewById(R.id.tv_vehicle_firstName)
    private val secondName : TextView = itemView.findViewById(R.id.tv_vehicle_secondName)
    private val star : ImageView = itemView.findViewById(R.id.iv_vehicleViewHolder_star)
    private val background : ImageView = itemView.findViewById(R.id.iv_car_background)
    private lateinit var car : Post
    fun bind(car: Post){
        this.car = car
        val arrOfName = car.title.split(' ')
        firstName.text = arrOfName.get(0) ?: "NULL"
        secondName.text = arrOfName.get(1) ?: "NULL"
        star.setImageResource(R.drawable.ic_star_favourite)
        Picasso.with(itemView.context).load(car.thumbnail).into(background)

        if(car.like){
            star.setImageResource(R.drawable.ic_star_favourite)
        }
        else{
            star.setImageResource(R.drawable.ic_star)
        }
    }

    init {
        itemView.setOnClickListener {
            NewsDetailsPage.start(itemView.context as Activity, car)
        }
    }
}