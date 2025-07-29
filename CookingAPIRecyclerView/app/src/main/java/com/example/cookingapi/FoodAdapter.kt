package com.example.cookingapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class FoodAdapter(val FoodList: MutableList<Obj>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val FoodImage: ImageView
        val NameText: TextView
        val AisleText: TextView
        val SpecialText: TextView

        init {
            FoodImage = view.findViewById(R.id.rngImage)
            NameText = view.findViewById(R.id.name)
            AisleText = view.findViewById(R.id.aisle)
            SpecialText = view.findViewById(R.id.special)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return FoodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        //holder.itemView
        Glide.with(holder.FoodImage.context)
            .load(FoodList[position].img)
            .into(holder.FoodImage)

        holder.NameText.text = FoodList[position].name
        holder.AisleText.text = "In aisle: " + FoodList[position].aisle
        holder.SpecialText.text = "Consistency/Texture: " + FoodList[position].special

    }


}