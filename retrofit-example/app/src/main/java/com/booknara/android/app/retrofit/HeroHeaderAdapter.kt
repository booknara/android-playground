package com.booknara.android.app.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class HeroHeaderAdapter(private val list: List<String>, private val context: Context) :
    RecyclerView.Adapter<HeroHeaderAdapter.HeroHeaderViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHeaderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout_heroes_header, parent, false)
        return HeroHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroHeaderViewHolder, position: Int) {
        val number = list[position]
        holder.textHeaderNumber.text = context.getString(R.string.num_heroes, number)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class HeroHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textHeaderTitle: TextView = itemView.findViewById(R.id.header_title)
        val textHeaderNumber: TextView = itemView.findViewById(R.id.header_number)
    }
}
