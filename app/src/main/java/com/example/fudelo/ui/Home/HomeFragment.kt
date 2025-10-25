package com.example.fudelo.ui.Home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R
import com.example.fudelo.ui.Recipe
import com.example.fudelo.ui.RecipeStep
import androidx.core.content.edit

class HomeFragment : Fragment() {

    // Наши RecyclerViewшки
    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView

    // Адаптеры
    private lateinit var catalogAdapter: AdapterCatalog
    private lateinit var favoritesAdapter: FavoriteAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    // Тут будут наши рецептики
    private val recipesList = mutableListOf<Recipe>()

    // Категории типа "все", "мясные", "овощные" и тд
    private val categories = listOf(
        CategoryItem("", "Все"),
        CategoryItem("meat", "Мясные"),
        CategoryItem("vegetables", "Овощные"),
        CategoryItem("dessert", "Десерты")
    )

    // Создаём вью
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_home, container, false).apply {
        catalogRecyclerView = findViewById(R.id.RecyCatalog)
        favoritesRecyclerView = findViewById(R.id.favorit)
        categoryRecyclerView = findViewById(R.id.menu)
    }

    // Тут все настройки после того как вью создалась
    @SuppressLint("NotifyDataSetChanged") //эта херння чтоб варнинг не давал
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем сохранённые избранные рецепты
        val prefs = requireContext().getSharedPreferences("fudelo_prefs", 0)
        val favoriteIds = prefs.getStringSet("favorite_recipes", emptySet()) ?: emptySet()

        // Загружаем примеры рецептов и помечаем избранные
        recipesList.addAll(sampleData().map { it.copy(isFavorite = favoriteIds.contains(it.id)) })

        // Каталог рецептов (горизонтальный список)
        catalogAdapter = AdapterCatalog(recipesList, ::openRecipe) { recipe ->
            val index = recipesList.indexOfFirst { it.id == recipe.id }
            if (index != -1) {
                recipesList[index] = recipe.copy(isFavorite = !recipe.isFavorite) // поменяли статус избранного
            }
            catalogAdapter.notifyDataSetChanged()
            favoritesAdapter.setFavorites(recipesList.filter { it.isFavorite }) // обновляем избранные
            prefs.edit {
                putStringSet(
                    "favorite_recipes",
                    recipesList.filter { it.isFavorite }.map { it.id }.toSet()
                )
            } // сохраняем в префс
        }
        catalogRecyclerView.apply {
            adapter = catalogAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        // Избранные рецепты (вертикальный список)
        favoritesAdapter = FavoriteAdapter(::openRecipe) { recipe ->
            val index = recipesList.indexOfFirst { it.id == recipe.id }
            if (index != -1) {
                recipesList[index] = recipe.copy(isFavorite = !recipe.isFavorite) // опять меняем избранные
            }
            catalogAdapter.notifyDataSetChanged()
            favoritesAdapter.setFavorites(recipesList.filter { it.isFavorite }) // обновляем список
            prefs.edit {
                putStringSet(
                    "favorite_recipes",
                    recipesList.filter { it.isFavorite }.map { it.id }.toSet()
                )
            } // сохранили
        }
        favoritesRecyclerView.apply {
            adapter = favoritesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Категории  (фильтр)
        categoryAdapter = CategoryAdapter(categories) { categoryItem ->
            // categoryItem может быть null, поэтому проверяем через ?.idType
            val type =
                categoryItem.idType.takeIf { it.isNotEmpty() } // если пустая строка -> null = все
            favoritesAdapter.applyFilter(type)
        }

        categoryRecyclerView.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        // По умолчанию выбираем "Все"
        categoryAdapter.selectCategoryById("")

    }

    // Открытие рецепта (мб сделаю)
    private fun openRecipe(recipe: Recipe) {
        // TODO: сделать открытие рецепта
    }

    // Примеры рецептов  для теста
    private fun sampleData() = listOf(
        Recipe(
            "1",
            "meat",
            "Пицца",
            "Вкусная пицца",
            "https://i.ytimg.com/vi/agbT44M6rnE/maxresdefault.jpg",
            mapOf(
                1 to RecipeStep("Замеси тесто", null, isTitlePage = true),
                2 to RecipeStep("Добавь начинку"),
                3 to RecipeStep("Запеки", null, isLastPage = true)
            )
        ),
        Recipe(
            "2",
            "vegetables",
            "Салат",
            "Лёгкий салат",
            "https://avatars.mds.yandex.net/i?id=b79d33ce6a79ba40ec7fdb5ef42c1056e55f0486-4078273-images-thumbs&n=13",
            mapOf(
                1 to RecipeStep("Порежь овощи", null, isTitlePage = true),
                2 to RecipeStep("Перемешай", null, isLastPage = true)
            )
        ),
        Recipe(
            "3",
            "dessert",
            "Торт",
            "Шоколадный торт",
            null,
            mapOf(
                1 to RecipeStep("Смешай ингредиенты", null, isTitlePage = true),
                2 to RecipeStep("Выпеки", null, isLastPage = true)
            )
        )
    )
}
