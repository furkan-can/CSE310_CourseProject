package com.example.thiefdetector.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thiefdetector.R
import com.example.thiefdetector.model.Photos
import com.squareup.picasso.Picasso

class ThiefDetectorAdapter(private val photoList : ArrayList<Photos>):
    RecyclerView.Adapter<ThiefDetectorAdapter.ThiefDetectorHolder>() {

    class ThiefDetectorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val time_thieff : TextView = itemView.findViewById(R.id.time_thief)
        val date_thieff : TextView = itemView.findViewById(R.id.date_thief)
        val image_thieff : ImageView = itemView.findViewById(R.id.image_thief)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThiefDetectorHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_item,
            parent,false)
        return ThiefDetectorHolder(itemView)    }

    override fun onBindViewHolder(holder: ThiefDetectorHolder, position: Int) {
        val curr_photo = photoList[position]

        holder.time_thieff.text = curr_photo.time
        holder.date_thieff.text = curr_photo.date
        Picasso.get().load(photoList[position].image).into(holder.image_thieff)
    }

    override fun getItemCount(): Int {
        return  photoList.size
    }
}