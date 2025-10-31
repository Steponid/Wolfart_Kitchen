package com.example.fudelo.ui.page.Fragment

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.fudelo.R

import com.example.fudelo.Recipe

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

        Glide
            .with(this)
            .load(recipe.imageUrl)
            .into(findViewById(R.id.image))


        // Добавляем список ингредиентов
        val ingredientsText = findViewById<TextView>(R.id.ingredientsText)
        val ingredientsList = recipe.ingredients.joinToString("\n") { "${it.name} — ${it.weight}" }
        ingredientsText.text = ingredientsList
    }

    companion object {
        fun newInstance(recipe: Recipe) = FirstFragment().apply {
            arguments = Bundle().apply { putParcelable("recipe", recipe) }
        }
    }
}
