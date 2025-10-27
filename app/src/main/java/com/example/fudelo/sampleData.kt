package com.example.fudelo

import com.example.fudelo.ui.Ingredient
import com.example.fudelo.ui.IntStep
import com.example.fudelo.ui.Recipe
import com.example.fudelo.ui.RecipeStep
fun sampleData() = listOf(
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
            ))
        ),
        videoUrl = "https://youtu.be/A3WYreVMk24"
    ),

    Recipe(
        "3",
        "pervoe",
        "Суп с квашеной капустой и курицей",
        "Сегодня у меня вкуснейший суп на обед, с квашеной капустой, на курице. Я купила куриное филе, чтобы супчик получился не очень жирным, но его можно заменить на куриную ножку или другой кусочек курицы.",
        "https://img.povar.ru/main/58/b6/e4/ec/sup_s_kvashenoi_kapustoi_i_kuricei-405095.JPG",
        difficulty = 3,
        timeMinutes = 80,
        ingredients = listOf(
            Ingredient("Куриное филе", "300г"),
            Ingredient("Квашаная капуста", "250г"),
            Ingredient("Картофель", "2шт"),
            Ingredient("Морковь", "1шт"),
            Ingredient("Лук", "1шт"),
            Ingredient("Растительное", "2-3ст ложек"),
            Ingredient("Лавровый лист", "1шт"),
            Ingredient("Перец душистый", "3-5шт"),
            Ingredient("Соль", "по вкусу"),
            Ingredient("Зелень", "По вкусу"),
        ),
        steps = listOf(
            IntStep(1, RecipeStep(
                text = "Подготовьте продукты. Вымойте куриное филе, положите его в кастрюлю, залейте холодной водой и проварите 30 минут.",
                imageUrl = "https://img.povar.ru/uploads/0a/2a/e8/0f/sup_s_kvashenoi_kapustoi_i_kuricei-405089.JPG",
            )),

            IntStep(2, RecipeStep(
                text = "Очистите лук и морковь, порежьте некрупно и обжарьте на двух столовых ложках масла до готовности.",
                imageUrl = "https://img.povar.ru/uploads/ab/4b/ab/68/sup_s_kvashenoi_kapustoi_i_kuricei-405090.JPG"
            )),

            IntStep(3, RecipeStep(
                text = "В готовый мясной бульон добавьте капусту, соль, перец душистый, лавровый лист и проварите 30 минут.",
                imageUrl = "https://img.povar.ru/uploads/30/7c/4c/74/sup_s_kvashenoi_kapustoi_i_kuricei-405091.JPG"
            )),

            IntStep(4, RecipeStep(
                text = "Добавьте картофель, предварительно очищенный и порезанный кусочками, проварите 15 минут. Добавьте жареные овощи и доварите суп до полной готовности.",
                imageUrl = "https://img.povar.ru/uploads/1c/7e/5c/c0/sup_s_kvashenoi_kapustoi_i_kuricei-405092.JPG"
            )),

            IntStep(5, RecipeStep(
                text = "Готовый суп заправьте свежей зеленью. Приятного аппетита! При подаче положите по чайной ложечке сметаны.",
                imageUrl = "https://img.povar.ru/uploads/d9/07/1d/c3/sup_s_kvashenoi_kapustoi_i_kuricei-405093.JPG",
            ))
        ),
        videoUrl = ""
    )
)

//Recipe(
//"3",
//"",
//"",
//"",
//"",
//difficulty = 1,
//timeMinutes = 1,
//ingredients = listOf(
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//Ingredient("", ""),
//),
//steps = listOf(
//IntStep(1, RecipeStep(
//text = "",
//imageUrl = "",
//isTitlePage = true
//)),
//
//IntStep(2, RecipeStep(
//text = "",
//imageUrl = ""
//)),
//
//IntStep(3, RecipeStep(
//text = "",
//imageUrl = "",
//isLastPage = true
//))
//),
//videoUrl = ""
//)