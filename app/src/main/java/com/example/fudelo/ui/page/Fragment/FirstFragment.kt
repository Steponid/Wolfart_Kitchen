package com.example.fudelo.ui.page.Fragment

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.example.fudelo.R

import com.example.fudelo.ui.Recipe

class FirstFragment : Fragment() {
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = requireArguments().getParcelable("recipe")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.page_intro, container, false).apply {

        findViewById<TextView>(R.id.title).text = recipe.title
        findViewById<TextView>(R.id.desc).text = recipe.description
        findViewById<TextView>(R.id.time).text = "${recipe.timeMinutes} мин"
        findViewById<TextView>(R.id.difficulty).text = "Сложность: ${"★".repeat(recipe.difficulty)}"

        Glide.with(this).load(recipe.imageUrl).into(findViewById(R.id.image))

        // Добавляем список ингредиентов
        val ingredientsContainer = findViewById<LinearLayout>(R.id.ingredientsContainer)
        recipe.ingredients.forEach { ingredient ->
            val tv = TextView(context).apply {
                text = "${ingredient.name} — ${ingredient.weight}"
                textSize = 16f
                setPadding(4, 4, 4, 4)
            }
            ingredientsContainer.addView(tv)
        }
    }

    companion object {
        fun newInstance(recipe: Recipe) = FirstFragment().apply {
            arguments = Bundle().apply { putParcelable("recipe", recipe) }
        }
    }
}
