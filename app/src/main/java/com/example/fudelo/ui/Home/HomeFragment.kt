package com.example.fudelo.ui.Home

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.fudelo.ui.Ingredient
import com.example.fudelo.ui.IntStep
import com.example.fudelo.ui.page.Page

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

    override fun onResume() {
        super.onResume()
        val prefs = requireContext().getSharedPreferences("fudelo_prefs", 0)
        val favoriteIds = prefs.getStringSet("favorite_recipes", emptySet()) ?: emptySet()
        recipesList.replaceAll { it.copy(isFavorite = favoriteIds.contains(it.id)) }
        catalogAdapter.notifyDataSetChanged()
        favoritesAdapter.setFavorites(recipesList.filter { it.isFavorite })
    }

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
        val intent = Intent(requireContext(), Page::class.java)
        intent.putExtra("recipe", recipe)
        startActivity(intent)
    }

    // Примеры рецептов  для теста
    private fun sampleData() = listOf(
        // Рецепт 2
        Recipe(
            "2",
            "complex",
            "Торт Наполеон с заварным кремом",
            "Классический Наполеон с множеством слоёв и тонким тестом.",
            "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13",
            difficulty = 5,
            timeMinutes = 180,
            ingredients = listOf(
                Ingredient("Мука", "500 г"),
                Ingredient("Масло сливочное", "250 г"),
                Ingredient("Молоко", "1 л"),
                Ingredient("Сахар", "200 г"),
                Ingredient("Яйца", "5 шт"),
                Ingredient("Ванильный сахар", "1 ч.л.")
            ),
            steps = listOf(
                IntStep(1, RecipeStep(
                    text = "Замешиваем тесто: смешиваем муку и холодное масло до крошки. Добавляем воду и вымешиваем до гладкости. Тесто должно быть эластичным, не липнуть к рукам.",
                    imageUrl = "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13",
                    isTitlePage = true
                )),
                IntStep(2, RecipeStep(
                    text = "Делим тесто на 10 равных частей, раскатываем каждый пласт очень тонко. Коржи должны быть максимально тонкие, иначе торт получится тяжёлым.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Napoleon_step2.jpg/800px-Napoleon_step2.jpg"
                )),
                IntStep(3, RecipeStep(
                    text = "Выпекаем каждый корж при 200°C до золотистого цвета. Остужаем на решётке.",
                    imageUrl = "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13"
                )),
                IntStep(4, RecipeStep(
                    text = "Готовим крем: молоко нагреваем, смешиваем с яйцами, сахаром и ванилью, доводим до густоты, постоянно помешивая."
                )),
                IntStep(5, RecipeStep(
                    text = "Собираем торт, промазывая каждый корж кремом, покрываем верхушку и бока. Ставим в холодильник минимум на 4 часа.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Napoleon_step5.jpg/800px-Napoleon_step5.jpg",
                    isLastPage = true
                ))
            ),
            videoUrl = "https://vk.com/video-161645474_456239337"
        ),
        Recipe(
            "3",
            "complex",
            "Айнтопф",
            "Блюдо представляет собой не что иное как густой суп чисто крестьянского происхождения, служащий полной трапезой.",
            "https://avatars.dzeninfra.ru/get-zen_doc/4721351/pub_61ba29c01a2fc9518da47c71_61bc6e2bc0fc4651c7b5e91c/scale_1200",
            difficulty = 5,
            timeMinutes = 180,
            ingredients = listOf(
                Ingredient("Охотничьи колбаски", "200 г"),
                Ingredient("Салями", "200 г"),
                Ingredient("Венские сардельки", "500 г"),
                Ingredient("Большая луковица", "1 шт."),
                Ingredient("Томатная паста", "1 ч.л"),
                Ingredient("Квашенная капуста", "500 г"),
            ),
            steps = listOf(
                IntStep(1, RecipeStep(
                    text = "Замешиваем тесто: смешиваем муку и холодное масло до крошки. Добавляем воду и вымешиваем до гладкости. Тесто должно быть эластичным, не липнуть к рукам.",
                    imageUrl = "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13",
                    isTitlePage = true
                )),
                IntStep(2, RecipeStep(
                    text = "Делим тесто на 10 равных частей, раскатываем каждый пласт очень тонко. Коржи должны быть максимально тонкие, иначе торт получится тяжёлым.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Napoleon_step2.jpg/800px-Napoleon_step2.jpg"
                )),
                IntStep(3, RecipeStep(
                    text = "Выпекаем каждый корж при 200°C до золотистого цвета. Остужаем на решётке.",
                    imageUrl = "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13"
                )),
                IntStep(4, RecipeStep(
                    text = "Готовим крем: молоко нагреваем, смешиваем с яйцами, сахаром и ванилью, доводим до густоты, постоянно помешивая."
                )),
                IntStep(5, RecipeStep(
                    text = "Собираем торт, промазывая каждый корж кремом, покрываем верхушку и бока. Ставим в холодильник минимум на 4 часа.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Napoleon_step5.jpg/800px-Napoleon_step5.jpg",
                    isLastPage = true
                ))
            ),
            videoUrl = "https://vk.com/video-161645474_456239337"
        ),

    )
}
