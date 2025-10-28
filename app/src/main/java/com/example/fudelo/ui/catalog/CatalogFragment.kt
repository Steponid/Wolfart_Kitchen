package com.example.fudelo.ui.catalog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SearchView
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R
import com.example.fudelo.parseSavedRecipe
import com.example.fudelo.sampleData
import com.example.fudelo.ui.Add
import com.example.fudelo.ui.Home.AdapterCatalog
import com.example.fudelo.ui.Home.CategoryAdapter
import com.example.fudelo.ui.Home.CategoryItem
import com.example.fudelo.ui.Recipe

class CatalogFragment : Fragment() {
    //меню
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    //каталог
    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var catalogAdapter: AdapterCatalog

    private val allRecipesList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false).apply {
            categoryRecyclerView = findViewById(R.id.catalog_menu)
            catalogRecyclerView = findViewById(R.id.recyclerCategorii)
            val search = findViewById<SearchView>(R.id.search)
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    catalogAdapter.filterSearch(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    catalogAdapter.filterSearch(newText ?: "")
                    return true
                }
            })

            val addRecept = findViewById<ImageButton>(R.id.addRecept)
            addRecept.setOnClickListener {
                startActivity(Intent(requireContext(), Add::class.java))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val prefs = requireContext().getSharedPreferences("fudelo_prefs", 0)
        val favoriteIds = prefs.getStringSet("favorite_recipes", null) ?: emptySet()

        val pref2 = requireContext().getSharedPreferences("my_recipes", Context.MODE_PRIVATE)
        val savedSet = pref2.getStringSet("recipes", emptySet()) ?: emptySet()

        // Очищаем и перезаполняем список
        allRecipesList.clear()

        // Добавляем образцы данных
        allRecipesList.addAll(sampleData().map { it.copy(isFavorite = favoriteIds.contains(it.id)) })

        // Добавляем пользовательские рецепты
        var nextId = allRecipesList.size + 1
        savedSet.forEach {
            val recipe = parseSavedRecipe(it, nextId)
            // Проверяем на дубликаты по ID
            if (allRecipesList.none { existing -> existing.id == recipe.id }) {
                allRecipesList.add(recipe.copy(isFavorite = favoriteIds.contains(recipe.id)))
                nextId++
            }
        }

        /**
         * настройка меню
         */
        categoryAdapter = CategoryAdapter(categories) { categoryItem ->
            val type = categoryItem.idType.takeIf { it.isNotEmpty() }
            catalogAdapter.applyFilter(type)
            categoryAdapter.selectCategoryById(categoryItem.idType)
        }

        catalogAdapter = AdapterCatalog(
            allRecipesList,
            { recipe -> toggleFavorite(recipe, prefs) },
            requireContext()
        )

        // Настройка ресайклеров
        catalogRecyclerView.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        categoryAdapter.selectCategoryById("")
    }

    private fun toggleFavorite(recipe: Recipe, prefs: android.content.SharedPreferences) {
        val index = allRecipesList.indexOfFirst { it.id == recipe.id }
        if (index != -1) {
            val updatedRecipe = allRecipesList[index].copy(isFavorite = !allRecipesList[index].isFavorite)
            allRecipesList[index] = updatedRecipe

            catalogAdapter.updateRecipe(recipe.id, updatedRecipe)

            prefs.edit {
                val newFavorites = allRecipesList.filter { it.isFavorite }.map { it.id }.toSet()
                putStringSet("favorite_recipes", newFavorites)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private val categories = listOf(
        CategoryItem("", "Все"),
        CategoryItem("pervoe", "Первое"),
        CategoryItem("vtoroe", "Второе"),
        CategoryItem("solati", "Салаты"),
        CategoryItem("disert", "Десерты")
    )
}