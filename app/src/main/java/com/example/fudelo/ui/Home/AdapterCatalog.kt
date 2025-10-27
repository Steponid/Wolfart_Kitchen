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

class AdapterCatalog(
    private val recipes: List<Recipe>,
    private val onFavoriteClick: (Recipe) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<AdapterCatalog.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_catalog_rec, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.textView9)
        private val img = itemView.findViewById<ImageView>(R.id.imageViewCatalogItem)
        private val likeButton = itemView.findViewById<ImageView>(R.id.favoriteButton)
        private val openButton = itemView.findViewById<ImageView>(R.id.OpenButton)

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            Glide.with(img.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.logo)
                .into(img)

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
                onFavoriteClick(recipe)
            }
        }
    }
}