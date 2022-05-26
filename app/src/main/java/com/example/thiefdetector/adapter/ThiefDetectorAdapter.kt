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

/**
 * @author Hümeyra Köseoğlu
 * @since 24.06.2022
 */

class ThiefDetectorAdapter(private val photoList : ArrayList<Photos>):
    RecyclerView.Adapter<ThiefDetectorAdapter.ThiefDetectorHolder>() {

    class ThiefDetectorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val time : TextView = itemView.findViewById(R.id.date_thief)
        val imagePath : ImageView = itemView.findViewById(R.id.image_thief)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThiefDetectorHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_item,
            parent,false)
        return ThiefDetectorHolder(itemView)    }

    override fun onBindViewHolder(holder: ThiefDetectorHolder, position: Int) {
        val curr_photo = photoList[position]

        holder.time.text = curr_photo.time!!.split(".")[0]
        Picasso.get().load(photoList[position].imagePath).into(holder.imagePath)
    }

    override fun getItemCount(): Int {
        return  photoList.size
    }
}