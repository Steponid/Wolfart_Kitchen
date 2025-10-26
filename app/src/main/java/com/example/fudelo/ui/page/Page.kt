package com.example.fudelo.ui.page

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.viewpager2.widget.ViewPager2
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe

class Page : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var stepText: TextView
    private lateinit var nextBtn: Button
    private lateinit var backBtn: Button
    private lateinit var favBtn: ImageView
    private lateinit var closeBtn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        window.statusBarColor = resources.getColor(R.color.accent2, theme)

        viewPager = findViewById(R.id.viewPagerRecipe)
        stepText = findViewById(R.id.currentStep)
        nextBtn = findViewById(R.id.nextStepBtn)
        backBtn = findViewById(R.id.backStepBtn)
        favBtn = findViewById(R.id.favorite)
        closeBtn = findViewById(R.id.close)

        val recipe = intent.getParcelableExtra<Recipe>("recipe") ?: return
        val adapter = PageAdapter(this, recipe)
        viewPager.adapter = adapter

        updateStepText(adapter)

        // Обновление текста шага при смене страницы
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateStepText(adapter)
            }
        })

        // Кнопка "Далее"
        nextBtn.setOnClickListener {
            val next = (viewPager.currentItem + 1).coerceAtMost(adapter.itemCount - 1)
            viewPager.currentItem = next
        }

        // Кнопка "Назад"
        backBtn.setOnClickListener {
            val prev = (viewPager.currentItem - 1).coerceAtLeast(0)
            viewPager.currentItem = prev
        }

        // Кнопка "Закрыть"
        closeBtn.setOnClickListener { finish() }

        // Кнопка "Фаворит"
        updateFavIcon(favBtn, recipe.isFavorite)
        favBtn.setOnClickListener {
            recipe.isFavorite = !recipe.isFavorite
            updateFavIcon(favBtn, recipe.isFavorite)
            saveFavorite(recipe.id, recipe.isFavorite)
        }
    }

    private fun updateStepText(adapter: PageAdapter) {
        val pos = viewPager.currentItem + 1
        stepText.text = "Шаг: $pos / ${adapter.itemCount}"
    }

    private fun updateFavIcon(favBtn: ImageView, isFav: Boolean) {
        favBtn.setImageResource(if (isFav) R.drawable.like_action else R.drawable.like)
    }

    private fun saveFavorite(recipeId: String, isFav: Boolean) {
        val prefs = getSharedPreferences("fudelo_prefs", 0)
        val favs = prefs.getStringSet("favorite_recipes", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (isFav) favs.add(recipeId) else favs.remove(recipeId)
        prefs.edit { putStringSet("favorite_recipes", favs) }
    }
}
