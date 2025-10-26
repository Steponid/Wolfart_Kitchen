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
            val type = categoryItem.idType.takeIf { it.isNotEmpty() } // если пустая строка -> null = все
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

    // данные загружаются от сюда

    private val categories = listOf(
        CategoryItem("", "Все"),
        CategoryItem("pervoe", "Первое"),
        CategoryItem("vtoroe", "Второе"),
        CategoryItem("solati", "Салаты"),
        CategoryItem("disert", "Десерты")
    )
    private fun sampleData() = listOf(
        Recipe(
            "0",
            "pervoe",
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
                Ingredient("Зелень свежая", "1 шт."),
                Ingredient("Картофель", "2 шт."),
            ),
            steps = listOf(
                IntStep(1, RecipeStep(
                    text = "Замешиваем тесто: смешиваем муку и холодное масло до крошки. Добавляем воду и вымешиваем до гладкости. Тесто должно быть эластичным, не липнуть к рукам.",
                    imageUrl = "https://avatars.mds.yandex.net/i?id=948bb467858d755bd8f221466051f2aaceaa6d6e-12537594-images-thumbs&n=13",
                    isTitlePage = true
                )),
                IntStep(2, RecipeStep(
                    text = "Делим тесто на 10 равных частей, раскатываем каждый пласт очень тонко. Коржи должны быть максимально тонкие, иначе торт получится тяжёлым.",
                    imageUrl = ""
                )),
                IntStep(3, RecipeStep(
                    text = "Выпекаем каждый корж при 200°C до золотистого цвета. Остужаем на решётке.",
                    imageUrl = ""
                )),
                IntStep(4, RecipeStep(
                    text = "Готовим крем: молоко нагреваем, смешиваем с яйцами, сахаром и ванилью, доводим до густоты, постоянно помешивая."
                )),
                IntStep(5, RecipeStep(
                    text = "Собираем торт, промазывая каждый корж кремом, покрываем верхушку и бока. Ставим в холодильник минимум на 4 часа.",
                    imageUrl = "",
                    isLastPage = true
                ))
            ),
            videoUrl = "https://vk.com/video-161645474_456239337"
        ),

        Recipe(
            "1",
            "pervoe",
            "Гороховый Суп",
            "Горохоховый супец просто ВАААААААХ",
            "https://img.povar.ru/uploads/34/e2/e0/15/gorohovii_sup_po-nemecki-706776.JPG",
            difficulty = 2,
            timeMinutes = 30,
            ingredients = listOf(
                Ingredient("Горох", "300г"),
                Ingredient("Вода", "2л"),
                Ingredient("Бекон Сырокопченый", "150г"),
                Ingredient("Лук", "1шт"),
                Ingredient("Морковь", "1шт"),
                Ingredient("Растительное масло", "1-2ст л"),
                Ingredient("Лавровый лист", "1-2шт"),
                Ingredient("Соль", "по вкусу"),
                Ingredient("Перец", "по вкусу"),
            ),
            steps = listOf(
                IntStep(1, RecipeStep(
                    text = "Горох можно не замачивать, но если вы не уверенны в его качестве, то лучше замочите на несколько часов, после чего хорошо промойте.",
                    imageUrl = "https://img.povar.ru/steps/6a/e9/fc/88/gorohovii_sup_po-nemecki-706768.JPG",
                    isTitlePage = true
                )),
                IntStep(2, RecipeStep(
                    text = "Переложите горох в кастрюлю и залейте водой. Варите до желаемой степени готовности.",
                    imageUrl = "https://img.povar.ru/steps/ea/0a/e0/85/gorohovii_sup_po-nemecki-706769.JPG"
                )),
                IntStep(3, RecipeStep(
                    text = "На сковороде разогрейте масло. Выложите нарезанные кубиками лук и морковь.",
                    imageUrl = "https://img.povar.ru/steps/c1/e3/7a/5f/gorohovii_sup_po-nemecki-706770.JPG"
                )),
                IntStep(5, RecipeStep(
                    text = "Обжарьте овощи до мягкости добавьте нарезанный бекон.",
                    imageUrl = "https://img.povar.ru/steps/05/7a/ac/30/gorohovii_sup_po-nemecki-706771.JPG"
                )),
                IntStep(6, RecipeStep(
                    text = "Обжарьте все вместе в течение 5-7 минут.",
                    imageUrl = "https://img.povar.ru/steps/78/af/cc/b9/gorohovii_sup_po-nemecki-706772.JPG"
                )),
                IntStep(7, RecipeStep(
                    text = "К готовому гороху добавьте соль по вкусу",
                    imageUrl = "https://img.povar.ru/steps/92/08/33/8d/gorohovii_sup_po-nemecki-706773.JPG"
                )),
                IntStep(8, RecipeStep(
                    text = "Добавьте в кастрюлю содержимое сковороды.",
                    imageUrl = "https://img.povar.ru/steps/80/89/ca/2c/gorohovii_sup_po-nemecki-706774.JPG"
                )),
                IntStep(9, RecipeStep(
                    text = "Добавьте необходимое количество воды (все зависит от желаемой густоты супа). Добавьте лавровый лист, доведите до кипения и проварите 5 минут. Гороховый суп по-немецки готов.",
                    imageUrl = "https://img.povar.ru/steps/55/3b/7a/4b/gorohovii_sup_po-nemecki-706775.JPG"
                )),

                IntStep(10, RecipeStep(
                    text = "готово",
                    imageUrl = "https://img.povar.ru/steps/34/e2/e0/15/gorohovii_sup_po-nemecki-706776.JPG",
                    isLastPage = true
                ))
            ),
            videoUrl = "https://youtu.be/A3WYreVMk24"
        ),

    )
}
