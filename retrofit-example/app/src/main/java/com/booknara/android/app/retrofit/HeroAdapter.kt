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
import com.booknara.android.app.retrofit.model.Hero
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
        holder.bind(context, hero, currentPosition, position)

        holder.textViewName.setOnClickListener {
            currentPosition = position
            notifyDataSetChanged()
        }
    }

    class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewRealName: TextView = itemView.findViewById(R.id.textViewRealName)
        private val textViewTeam: TextView = itemView.findViewById(R.id.textViewTeam)
        private val textViewFirstAppearance: TextView = itemView.findViewById(R.id.textViewFirstAppearance)
        private val textViewCreatedBy: TextView = itemView.findViewById(R.id.textViewCreatedBy)
        private val textViewPublisher: TextView = itemView.findViewById(R.id.textViewPublisher)
        private val textViewBio: TextView = itemView.findViewById(R.id.textViewBio)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        
        fun bind(context: Context, hero: Hero, currentPosition: Int, position: Int) {
            textViewName.text = hero.name
            textViewRealName.text = hero.realname
            textViewTeam.text = hero.team
            textViewFirstAppearance.text = hero.firstappearance
            textViewCreatedBy.text = hero.createdby
            textViewPublisher.text = hero.publisher
            textViewBio.text = hero.bio

            Glide.with(context).load(hero.imageurl).into(imageView)
            linearLayout.visibility = View.GONE

            if (currentPosition == position) {
                val animation = AnimationUtils.loadAnimation(context, R.anim.slide_down)
                linearLayout.visibility = View.VISIBLE
                linearLayout.animation = animation
            }
        }
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
