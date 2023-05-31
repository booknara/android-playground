package com.booknara.android.app.nasa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.nasa.model.Photo
import com.bumptech.glide.Glide

class NasaRecyclerView: ListAdapter<Photo, NasaRecyclerView.NasaViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return NasaViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: NasaViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class NasaViewHolder(private val context: Context, itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<AppCompatImageView>(R.id.image_view)
        private val textView = itemView.findViewById<TextView>(R.id.text_view)
        fun bind(item: Photo) {
            // Glide
            Glide.with(context)
                .load(item.imgSrc)
                .centerCrop()
                .into(imageView)
            textView.text = item.id.toString()
        }
    }
    
    object DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}
