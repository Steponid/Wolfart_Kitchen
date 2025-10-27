package com.example.fudelo.ui.Home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe
import com.example.fudelo.ui.page.Page

class FavoriteAdapter(
    private var favorites: List<Recipe>,
    private val onFavoriteClick: (Recipe) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var allFavorites: List<Recipe> = favorites
    private var displayedFavorites: List<Recipe> = favorites

    // обновление
    fun updateFavorites(newFavorites: List<Recipe>) {
        allFavorites = newFavorites
        displayedFavorites = newFavorites
        notifyDataSetChanged()
    }
    // фильтр
    fun applyFilter(categoryId: String?) {
        displayedFavorites = if (categoryId.isNullOrEmpty()) {
            allFavorites
        } else {
            allFavorites.filter { it.idType == categoryId }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_catalog_rec, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(displayedFavorites[position])
    }

    override fun getItemCount(): Int = displayedFavorites.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.textView9)
        private val image = view.findViewById<ImageView>(R.id.imageViewCatalogItem)
        private val likeButton = view.findViewById<ImageView>(R.id.favoriteButton)
        private val openButton = view.findViewById<ImageView>(R.id.OpenButton)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            Glide.with(image.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.logo)
                .into(image)

            likeButton.setImageResource(
                if (recipe.isFavorite) R.drawable.like_action else R.drawable.like
            )

            openButton.setOnClickListener {
                // делаем переход
                val intent = Intent(context, Page::class.java)
                intent.putExtra("recipe", recipe)
                context.startActivity(intent)
            }
            likeButton.setOnClickListener {
                //передаем данные в фрагмент вся логика тама
                onFavoriteClick(recipe)
            }
        }
    }
}

