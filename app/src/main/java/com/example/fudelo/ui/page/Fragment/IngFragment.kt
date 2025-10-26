package com.example.fudelo.ui.page.Fragment

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe

class IngFragment : Fragment() {
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = requireArguments().getParcelable("recipe")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.page_ingredients, container, false).apply {
        val listContainer = findViewById<LinearLayout>(R.id.ingredientsList)
        recipe.ingredients.forEach {
            val item = TextView(context).apply {
                text = "${it.name} — ${it.weight}"
                textSize = 18f
                setPadding(8, 8, 8, 8)
            }
            listContainer.addView(item)
        }
    }

    companion object {
        fun newInstance(recipe: Recipe) = IngFragment().apply {
            arguments = Bundle().apply { putParcelable("recipe", recipe) }
        }
    }
}
