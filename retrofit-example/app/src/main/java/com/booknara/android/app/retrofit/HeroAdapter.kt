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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class HeroAdapter(private val heroes: List<Hero>, private val context: Context): RecyclerView.Adapter<HeroAdapter.HeroViewHolder>() {
    private var currentPosition: Int = 0;
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_layout_heroes, parent, false)
        return HeroViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = heroes[position]
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
            // updateList()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    private fun updateList() {
        val callback = HeroDiffCallback(heroes, heroes)
        val diffResult = DiffUtil.calculateDiff(callback)
//        this.heroes.clear()
//        this.heroes.addAll(heroes)
        diffResult.dispatchUpdatesTo(this)
        
    }
    class HeroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewRealName:TextView = itemView.findViewById(R.id.textViewRealName)
        val textViewTeam:TextView = itemView.findViewById(R.id.textViewTeam)
        val textViewFirstAppearance:TextView = itemView.findViewById(R.id.textViewFirstAppearance)
        val textViewCreatedBy: TextView = itemView.findViewById(R.id.textViewCreatedBy)
        val textViewPublisher:TextView = itemView.findViewById(R.id.textViewPublisher)
        val textViewBio:TextView = itemView.findViewById(R.id.textViewBio)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
    }
    
    class HeroDiffCallback(val oldHeroes: List<Hero>, val newHeroes: List<Hero>): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldHeroes.size
        }

        override fun getNewListSize(): Int {
            return newHeroes.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldHeroes.get(oldItemPosition).name == newHeroes.get(newItemPosition).name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldHeroes.get(oldItemPosition).realname == newHeroes.get(newItemPosition).realname
        }
    } 
}
