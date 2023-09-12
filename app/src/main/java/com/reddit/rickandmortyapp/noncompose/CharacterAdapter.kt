package com.reddit.rickandmortyapp.noncompose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reddit.rickandmortyapp.R
import com.reddit.rickandmortyapp.network.RickAndMortyCharacter

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    private val data =  mutableListOf<RickAndMortyCharacter>()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imageView: ImageView
        private lateinit var name: TextView

        fun bind(character: RickAndMortyCharacter) {
            name = itemView.findViewById(R.id.name)
            imageView = itemView.findViewById(R.id.icon)
            name.text = character.name
            character.image?.let {
                Glide.with(imageView.context).load(it).into(imageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItems(newData : List<RickAndMortyCharacter>) {
        // different logic for pagination
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun addItems(nextPage: List<RickAndMortyCharacter>) {
        data.addAll(nextPage)
        notifyItemRangeChanged(data.size - 1, nextPage.size)
    }
}