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


class HeroAdapter(private val context: Context) :
    ListAdapter<Hero, HeroAdapter.HeroViewHolder>(HeroDiffCallback) {
    private var currentPosition: Int = 0
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout_heroes, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.textViewName.text = hero.name
        holder.textViewRealName.text = hero.realname
        holder.textViewTeam.text = hero.team
        holder.textViewFirstAppearance.text = hero.firstappearance
        holder.textViewCreatedBy.text = hero.createdby
        holder.textViewPublisher.text = hero.publisher
        holder.textViewBio.text = hero.bio

        Glide.with(context).load(hero.imageurl).into(holder.imageView)
        holder.linearLayout.visibility = View.GONE

        if (currentPosition == position) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
            holder.linearLayout.visibility = View.VISIBLE
            holder.linearLayout.animation = animation
        }

        holder.textViewName.setOnClickListener {
            currentPosition = position
            notifyDataSetChanged()
        }
    }

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewRealName: TextView = itemView.findViewById(R.id.textViewRealName)
        val textViewTeam: TextView = itemView.findViewById(R.id.textViewTeam)
        val textViewFirstAppearance: TextView = itemView.findViewById(R.id.textViewFirstAppearance)
        val textViewCreatedBy: TextView = itemView.findViewById(R.id.textViewCreatedBy)
        val textViewPublisher: TextView = itemView.findViewById(R.id.textViewPublisher)
        val textViewBio: TextView = itemView.findViewById(R.id.textViewBio)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
    }

    object HeroDiffCallback : DiffUtil.ItemCallback<Hero>() {
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }
    }
}
