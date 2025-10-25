package com.example.fudelo.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterCatalog
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //иницизилиация
        recyclerView = view.findViewById(R.id.RecyCatalog)
        menuRecyclerView = view.findViewById(R.id.menu)
        favoriteRecyclerView = view.findViewById(R.id.favorit)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // тест данные
        val catalogItems = listOf(
            catalog(1, "Пункт 1", R.drawable.ic_launcher_foreground),
            catalog(2, "Пункт 2", R.drawable.ic_launcher_foreground),
            catalog(3, "Пункт 3", R.drawable.ic_launcher_foreground)
        )

        adapter = AdapterCatalog(catalogItems) { item ->

        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // тест данные
        val categories = listOf(
            CategoryItem("all", "Все"),
            CategoryItem("meat", "Мясные"),
            CategoryItem("seafood", "Рыбные"),
            CategoryItem("fruit", "Фруктовые"),
            CategoryItem("vegetables", "Овощные"),
            CategoryItem("dessert", "Десерты")
        )

        categoryAdapter = CategoryAdapter(categories) { selectedCategory ->
            println("Выбрана категория: ${selectedCategory.text} (${selectedCategory.idType})")
        }

        menuRecyclerView.adapter = categoryAdapter
        menuRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter.selectCategoryById("all")

        val url = "https://avatars.mds.yandex.net/i?id=2a918a6bd9c173b2ab967f16827a1909983a805e-5376016-images-thumbs&n=13"


    }
}