package com.example.fudelo.ui.FragmenMain

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R
import com.example.fudelo.parseSavedRecipe
import com.example.fudelo.sampleData
import com.example.fudelo.ui.Adpters.CatalogAdapter
import com.example.fudelo.ui.Adpters.CategoryAdapter
import com.example.fudelo.ui.Adpters.CategoryItem
import com.example.fudelo.ui.Adpters.FavoriteAdapter
import com.example.fudelo.Recipe

class HomeFragment : Fragment() {

    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    private lateinit var catalogAdapter: CatalogAdapter
    private lateinit var favoritesAdapter: FavoriteAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    private val allRecipesList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            catalogRecyclerView = findViewById(R.id.RecyCatalog)
            favoritesRecyclerView = findViewById(R.id.favorit)
            categoryRecyclerView = findViewById(R.id.menu)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Загружаем данные
        loadData()
    }

    private fun loadData() {
        val prefs = requireContext().getSharedPreferences("fudelo_prefs", 0)
        val favoriteIds = prefs.getStringSet("favorite_recipes", null) ?: emptySet()

        val prefs2 = requireContext().getSharedPreferences("my_recipes", Context.MODE_PRIVATE)
        val savedSet = prefs2.getStringSet("recipes", emptySet()) ?: emptySet()

        // Очищаем и перезаполняем список
        allRecipesList.clear()

        // Добавляем образцы данных
        allRecipesList.addAll(sampleData().map { it.copy(isFavorite = favoriteIds.contains(it.id.toString())) })

        // Добавляем пользовательские рецепты
        var nextId = allRecipesList.size + 1
        savedSet.forEach {
            val recipe = parseSavedRecipe(it, nextId)
            // Проверяем на дубликаты по ID
            if (allRecipesList.none { existing -> existing.id == recipe.id }) {
                allRecipesList.add(recipe.copy(isFavorite = favoriteIds.contains(recipe.id.toString())))
                nextId++
            }
        }

        // Инициализация адаптеров
        catalogAdapter = CatalogAdapter(
            allRecipesList,
            { recipe -> toggleFavorite(recipe, prefs) },
            requireContext()
        )

        favoritesAdapter = FavoriteAdapter(
            allRecipesList.filter { it.isFavorite },
            { recipe -> toggleFavorite(recipe, prefs) },
            requireContext()
        )

        categoryAdapter = CategoryAdapter(categories) { categoryItem ->
            val type = categoryItem.idType.takeIf { it.isNotEmpty() }
            favoritesAdapter.applyFilter(type)
            categoryAdapter.selectCategoryById(categoryItem.idType)
        }

        // Настройка ресайклеров
        catalogRecyclerView.apply {
            adapter = catalogAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        favoritesRecyclerView.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        categoryAdapter.selectCategoryById("")
    }

    private fun toggleFavorite(recipe: Recipe, prefs: SharedPreferences) {
        val index = allRecipesList.indexOfFirst { it.id == recipe.id }
        if (index != -1) {
            // Переключаем состояние избранного
            val updatedRecipe = allRecipesList[index].copy(isFavorite = !allRecipesList[index].isFavorite)
            allRecipesList[index] = updatedRecipe

            // Уведомляем адаптеры
            catalogAdapter.notifyItemChanged(index)
            favoritesAdapter.updateFavorites(allRecipesList.filter { it.isFavorite })

            // Обновляем SharedPreferences
            prefs.edit {
                val newFavorites = allRecipesList.filter { it.isFavorite }.map { it.id.toString() }.toSet()
                putStringSet("favorite_recipes", newFavorites)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // Данные для меню
    private val categories = listOf(
        CategoryItem("", "Все"),
        CategoryItem("pervoe", "Первое"),
        CategoryItem("vtoroe", "Второе"),
        CategoryItem("solati", "Салаты"),
        CategoryItem("disert", "Десерты")
    )

}