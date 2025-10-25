package com.example.fudelo.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe

class FavoriteAdapter(
    private val onRecipeClick: (Recipe) -> Unit,
    private val onFavoriteClick: (Recipe) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val allFavorites = mutableListOf<Recipe>() // все избранные
    private val filteredFavorites = mutableListOf<Recipe>() // для отображения после фильтра

    fun setFavorites(list: List<Recipe>) {
        allFavorites.clear()
        allFavorites.addAll(list)
        // по умолчанию показываем все
        applyFilter(null)
    }

    fun applyFilter(categoryId: String?) {
        filteredFavorites.clear()
        filteredFavorites.addAll(
            allFavorites.filter { categoryId == null || it.idType == categoryId }
        )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_catalog_rec, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(filteredFavorites[position])

    override fun getItemCount() = filteredFavorites.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.textView9)
        private val image: ImageView = view.findViewById(R.id.imageViewCatalogItem)
        private val likeButton: ImageButton = view.findViewById(R.id.favoriteButton)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            Glide.with(image.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.logo)
                .into(image)

            likeButton.setImageResource(
                if (recipe.isFavorite) R.drawable.catalog else R.drawable.home
            )

            itemView.setOnClickListener { onRecipeClick(recipe) }

            likeButton.setOnClickListener {
                onFavoriteClick(recipe)
            }
        }
    }
}

