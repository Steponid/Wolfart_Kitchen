package com.example.fudelo.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fudelo.R

data class RecipeItem(
    val id: Int, // ID блюда
    val idType: String, // ID категории для фильтрации
    val title: String, // Название блюда
    val stepsCount: Int, // Количество шагов
    val stepTexts: Map<Int, String>, // Тексты шагов (номер шага, значение - текст)
    val imageUrl: String?, // Ссылка на изображение блюда
    val videoUrl: String?, // Ссылка на видео
    val stepImages: Map<Int, String>?, // Ссылки на изображения шагов (номер шага, значение - ссылка)
    val isFavorite: Boolean // Флаг избранного
)

class FavoriteAdapter(
    private var recipes: List<RecipeItem>, // Список блюд
    private val onRecipeClick: (RecipeItem) -> Unit, // Обработчик клика по блюду
    private val onLikeClick: (RecipeItem) -> Unit // Обработчик клика по кнопке лайка
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    // Получаем список избранных блюд (только те, где isFavorite = true)
    fun getFavoriteRecipes(): List<RecipeItem> {
        return recipes.filter { it.isFavorite }
    }

    // Обновляем список блюд
    fun updateRecipes(newRecipes: List<RecipeItem>) {
        this.recipes = newRecipes
        notifyDataSetChanged()
    }

    // Обновляем статус избранного для конкретного блюда
    fun updateFavoriteStatus(recipeId: Int, isFavorite: Boolean) {
        val index = recipes.indexOfFirst { it.id == recipeId }
        if (index != -1) {
            val updatedRecipe = recipes[index].copy(isFavorite = isFavorite)
            val updatedList = recipes.toMutableList().apply {
                this[index] = updatedRecipe
            }
            this.recipes = updatedList
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_catalog_rec, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        // Обрабатываем клик по блюду (открываем рецепт)
        holder.itemView.setOnClickListener {
            onRecipeClick(recipe)
        }

        // Обрабатываем клик по кнопке лайка
        holder.likeButton.setOnClickListener {
            // Меняем статус избранного
            val newIsFavorite = !recipe.isFavorite
            // Обновляем в адаптере
            updateFavoriteStatus(recipe.id, newIsFavorite)
            // Вызываем callback для обновления данных в фрагменте
            onLikeClick(recipe.copy(isFavorite = newIsFavorite))
        }
    }

    override fun getItemCount(): Int = recipes.size

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        private val titleText: TextView = itemView.findViewById(R.id.textView9)
        val likeButton: Button = itemView.findViewById(R.id.buttonLike)

        fun bind(recipe: RecipeItem) {
            // Устанавливаем название блюда
            titleText.text = recipe.title

            // Устанавливаем изображение блюда
            if (!recipe.imageUrl.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(recipe.imageUrl)
                    .into(imageView)
            } else {
                // Установите дефолтное изображение, если ссылка пустая
                imageView.setImageResource(R.drawable.logo)
            }

            if (recipe.isFavorite) {
                likeButton.setBackgroundResource(R.drawable.shape_button_no) // Фон для избранного
            } else {
                likeButton.setBackgroundResource(R.drawable.shape_button) // Фон для не избранного
            }
        }
    }
}