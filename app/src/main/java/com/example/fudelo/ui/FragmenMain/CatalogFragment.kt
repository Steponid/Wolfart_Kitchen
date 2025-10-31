package com.example.fudelo.ui.FragmenMain

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R
import com.example.fudelo.Recipe
import com.example.fudelo.parseSavedRecipe
import com.example.fudelo.ui.Add
import com.example.fudelo.ui.Adpters.CatalogAdapter
import com.example.fudelo.ui.Adpters.CategoryAdapter
import com.example.fudelo.ui.Adpters.CategoryItem
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CatalogFragment : Fragment() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var addReceptButton: ImageButton

    private lateinit var categoryAdapter: CategoryAdapter
    private var catalogAdapter: CatalogAdapter? = null

    private val allRecipesList = mutableListOf<Recipe>()

    private val categories = listOf(
        CategoryItem("", "Все"),
        CategoryItem("pervoe", "Первое"),
        CategoryItem("vtoroe", "Второе"),
        CategoryItem("solati", "Салаты"),
        CategoryItem("disert", "Десерты")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false).apply {
            categoryRecyclerView = findViewById(R.id.catalog_menu)
            catalogRecyclerView = findViewById(R.id.recyclerCategorii)
            searchView = findViewById(R.id.search)
            addReceptButton = findViewById(R.id.addRecept)

            catalogAdapter = CatalogAdapter(
                mutableListOf(),
                { recipe -> toggleFavorite(recipe, requireContext()) },
                requireContext()
            )

            catalogRecyclerView.apply {
                adapter = catalogAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

            setupSearchListener()
            setupAddButton()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        val prefs = requireContext().getSharedPreferences("fudelo_prefs", Context.MODE_PRIVATE)
        val favoriteIds = prefs.getStringSet("favorite_recipes", null) ?: emptySet()

        val pref2 = requireContext().getSharedPreferences("my_recipes", Context.MODE_PRIVATE)
        val savedSet = pref2.getStringSet("recipes", emptySet()) ?: emptySet()

        allRecipesList.clear()

        val db = Firebase.firestore
        db.collection("recept")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    try {
                        val recipe = document.toObject(Recipe::class.java)
                        if (recipe != null) {
                            val id = recipe.id.takeIf { it != 0 } ?: document.id.toIntOrNull() ?: 0
                            val finalRecipe = recipe.copy(
                                id = id,
                                isFavorite = favoriteIds.contains(id.toString())
                            )
                            allRecipesList.add(finalRecipe)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("Ошибка парсинга рецепта ${document.id}: ${e.message}")
                    }
                }

                var nextId = allRecipesList.size + 1
                savedSet.forEach {
                    try {
                        val localRecipe = parseSavedRecipe(it, nextId)
                        if (allRecipesList.none { existing -> existing.id == localRecipe.id }) {
                            allRecipesList.add(localRecipe.copy(isFavorite = favoriteIds.contains(localRecipe.id.toString())))
                            nextId++
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                setupAdapters()

            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
                println("Ошибка загрузки рецептов: ${exception.message}")
            }
    }

    private fun setupAdapters() {
        catalogAdapter?.updateData(allRecipesList)

        categoryAdapter = CategoryAdapter(categories) { categoryItem ->
            val type = categoryItem.idType.takeIf { it.isNotEmpty() }
            catalogAdapter?.applyFilter(type)
            categoryAdapter.selectCategoryById(categoryItem.idType)
        }

        categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        categoryAdapter.selectCategoryById("")
    }

    private fun setupSearchListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                catalogAdapter?.filterSearch(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                catalogAdapter?.filterSearch(newText ?: "")
                return true
            }
        })
    }

    private fun setupAddButton() {
        addReceptButton.setOnClickListener {
            startActivity(Intent(requireContext(), Add::class.java))
        }
    }

    private fun toggleFavorite(recipe: Recipe, context: Context) {
        val prefs = context.getSharedPreferences("fudelo_prefs", Context.MODE_PRIVATE)
        val index = allRecipesList.indexOfFirst { it.id == recipe.id }
        if (index != -1) {
            val updatedRecipe = allRecipesList[index].copy(isFavorite = !allRecipesList[index].isFavorite)
            allRecipesList[index] = updatedRecipe

            catalogAdapter?.updateRecipe(recipe.id, updatedRecipe)

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
}