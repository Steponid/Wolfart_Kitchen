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
        private val title = view.findViewById<TextView>(R.id.textView9)
        private val image = view.findViewById<ImageView>(R.id.imageViewCatalogItem)
        private val likeButton = view.findViewById<ImageView>(R.id.favoriteButton)
        private val OpenButton = view.findViewById<ImageView>(R.id.OpenButton)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            Glide.with(image.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.logo)
                .into(image)

            likeButton.setImageResource(
                if (recipe.isFavorite) R.drawable.like_action else R.drawable.like
            )

            OpenButton.setOnClickListener { onRecipeClick(recipe) }

            likeButton.setOnClickListener {
                onFavoriteClick(recipe)
            }
        }
    }
}

