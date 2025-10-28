package com.example.fudelo

import com.example.fudelo.ui.Ingredient
import com.example.fudelo.ui.IntStep
import com.example.fudelo.ui.Recipe
import com.example.fudelo.ui.RecipeStep

fun parseSavedRecipe(str: String, nextId: Int): Recipe {
    val parts = str.split("|")
    val name = parts.getOrNull(0) ?: ""
    val type = parts.getOrNull(1) ?: ""
    val desc = parts.getOrNull(2) ?: ""
    val img = parts.getOrNull(3) ?: ""
    val ingredients = parts.getOrNull(4)?.split(";")?.map {
        val parts = it.split("-").map { p -> p.trim() }
        if (parts.size >= 2) {
            Ingredient(parts[0], parts[1])
        } else {
            Ingredient(it, "")
        }
    } ?: emptyList()
    val steps = parts.getOrNull(5)?.split(";")?.mapIndexed { i, s ->
        IntStep(i+1, RecipeStep(s, ""))
    } ?: emptyList()

    return Recipe(
        nextId.toString(),
        type,
        name,
        desc,
        img,
        0,
        0,
        ingredients,
        steps,
        null
    )
}
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
        "",
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
    ),

    Recipe(
    "41",
    "pervoe",
    "Немецкий картофельный суп",
    "Очень вкусное картофельное пюре\n81 ККал",
    "https://img.povar.ru/main-micro/f1/4f/96/b0/nemeckii_kartofelnii_sup-668626.JPG",
    difficulty = 2,
    timeMinutes = 60,
    ingredients = listOf(
        Ingredient("Картофель", "400-500грамм"),
        Ingredient("Морковь", "1шт"),
        Ingredient("Лук репчатый", "1/2шт"),
        Ingredient("Лавновый лист", "1-2шт"),
        Ingredient("Сливки", "250-300мл"),
        Ingredient("Зелень", "1/4лучка"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "Подготовьте все ингредиенты.",
        imageUrl = "https://img.povar.ru/uploads/6e/74/46/20/nemeckii_kartofelnii_sup-668619.JPG",
        )),

        IntStep(2, RecipeStep(
        text = "Картофель и морковь помойте, очистите от кожуры, нарежьте крупными ломтиками. Лук нарежьте на четвертинки.",
        imageUrl = "https://img.povar.ru/uploads/10/fe/9c/72/nemeckii_kartofelnii_sup-668620.JPG"
        )),

        IntStep(3, RecipeStep(
        text = "Переложите овощи в кастрюлю. Добавьте специи, соль, влейте воду. Доведите до кипения. Варите на среднем огне до мягкости овощей.",
        imageUrl = "https://img.povar.ru/uploads/65/f1/e3/34/nemeckii_kartofelnii_sup-668621.JPG",
        )),

        IntStep(4, RecipeStep(
            text = "Вытащите морковь, перец горошком и лавровый лист из супа. Слейте жидкость, а картофель с луком раздавите толкушкой или вилкой.",
            imageUrl = "https://img.povar.ru/uploads/e4/3d/da/5e/nemeckii_kartofelnii_sup-668622.JPG",
        )),

        IntStep(5, RecipeStep(
            text = "Влейте картофельный отвар обратно. Добавьте сливки. Вливать жидкость лучше не всю сразу, а ориентироваться на желаемую консистенцию. Нарежьте вареную морковь кружочками и добавьте в суп. Посолите и поперчите по вкусу. Доведите до кипения.",
            imageUrl = "https://img.povar.ru/uploads/70/e3/86/ce/nemeckii_kartofelnii_sup-668623.JPG",
        )),

        IntStep(6, RecipeStep(
            text = "Колбасу нарежьте кружочками и обжарьте.",
            imageUrl = "https://img.povar.ru/uploads/a0/88/fa/74/nemeckii_kartofelnii_sup-668624.JPG",
        )),

        IntStep(7, RecipeStep(
            text = "Подавайте суп с жареной колбаской и мелко порубленной зеленью. Приятного аппетита!",
            imageUrl = "https://img.povar.ru/uploads/2a/b3/02/ba/nemeckii_kartofelnii_sup-668625.JPG",
        )),

        ),

    videoUrl = "https://yandex.ru/video/preview/305467926891946233"
    ),

    Recipe(
    "4",
    "vtoroe",
    "Картофельные клецки",
    "очень необычные клетски из картофиля\n143 ККал",
    "https://img.povar.ru/main-micro/d2/12/56/2b/kartofelnie_klyocki-489437.jpg",
    difficulty = 4,
    timeMinutes = 60,
    ingredients = listOf(
        Ingredient("Картофель", "750гр"),
        Ingredient("Мука", "200гр"),
        Ingredient("Яйцо", "1шт"),
        Ingredient("Сливочное масло", "1ст ложка"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "1. Картофель вымойте как следует, залейте водой и отварите до мягкости.",
        imageUrl = "https://img.povar.ru/uploads/92/46/2a/8b/kartofelnie_klyocki-489419.jpg",
        )),

        IntStep(2, RecipeStep(
        text = "2. Остудите под холодной водой и очистите.",
        imageUrl = "https://img.povar.ru/uploads/1d/6a/90/ed/kartofelnie_klyocki-489420.jpg"
        )),

        IntStep(3, RecipeStep(
        text = "3. Разомните картофель в пюре, вбейте яйцо, добавьте соль и перец. Всыпьте половину муки и перемешайте.",
        imageUrl = "https://img.povar.ru/uploads/d8/2a/6e/d1/kartofelnie_klyocki-489421.jpg",
        )),

        IntStep(4, RecipeStep(
            text = "4. Переложите все на рабочий стол. Подсыпайте муку, вымешивая тесто до однородности.",
            imageUrl = "https://img.povar.ru/uploads/1d/6a/dc/8b/kartofelnie_klyocki-489422.jpg",
        )),

        IntStep(5, RecipeStep(
            text = "5. Сформируйте небольшие клецки. Доведите до кипения подсоленную воду, опустите туда клецки на пару минут. После обсушите.",
            imageUrl = "https://img.povar.ru/uploads/63/17/ff/44/kartofelnie_klyocki-489425.jpg",
        )),

        IntStep(6, RecipeStep(
            text = "6. Перед подачей картофельные клёцки можно поджарить на сковороде с небольшим количеством масла до румяности с двух сторон.",
            imageUrl = "https://img.povar.ru/uploads/f4/14/f9/a8/kartofelnie_klyocki-489427.jpg",
        )),

        IntStep(7, RecipeStep(
            text = "7. Измельчите помидорки, добавьте соль и перец, немного оливкового масла, зелень, можно чуточку сыра. Добавьте клецки и подавайте к столу.",
            imageUrl = "https://img.povar.ru/uploads/72/50/4c/89/kartofelnie_klyocki-489436.jpg",
        )),

        ),
        videoUrl = "https://vkvideo.ru/video-5789730_456241260?ref_domain=yandex-video.naydex.net"
    ),

    Recipe(
    "5",
    "vtoroe",
    "Шницель",
    "Шницель — это блюдо традиционной венской кухни, которое традиционное готовится исключительно из телятины. Мясо обваливают в панировке (сухари и мука), а затем жарят в масле.\n250 ККал",
    "https://img.povar.ru/main-micro/a4/8c/4e/8a/shnicel_klassicheskii_recept-328829.JPG",
    difficulty = 4,
    timeMinutes = 40,
    ingredients = listOf(
        Ingredient("Телятина", "700гр"),
        Ingredient("Панировочные сухари", "1стакан"),
        Ingredient("Мука", "1стакан"),
        Ingredient("Яйцо", "1стакан"),
        Ingredient("Молоко", "3 ст ложки"),
        Ingredient("Растительное Масло", "100мл"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "Для приготовления шницеля классического нам необходимо взять мясо телятины. Нарежьте его на пластины.",
        imageUrl = "https://img.povar.ru/uploads/7b/0b/3e/2b/shnicel_klassicheskii_recept-328816.JPG",
        )),

        IntStep(2, RecipeStep(
        text = "Мясо отбейте молоточком с двух сторон.",
        imageUrl = "https://img.povar.ru/uploads/c6/fd/19/fe/shnicel_klassicheskii_recept-328817.JPG"
        )),

        IntStep(3, RecipeStep(
            text = "Посолите и поперчите кусочек с обеих сторон.",
            imageUrl = "https://img.povar.ru/uploads/82/52/1c/69/shnicel_klassicheskii_recept-328818.JPG"
        )),

        IntStep(4, RecipeStep(
            text = "В одной мисочке взбейте яйца с молоком. В другую насыпьте муку, а в третью — панировочные сухари.",
            imageUrl = "https://img.povar.ru/uploads/93/10/0b/6e/shnicel_klassicheskii_recept-328819.JPG"
        )),

        IntStep(5, RecipeStep(
            text = "Окуните шницель во взбитое яйцо, потом в муку, потом снова в яйцо, потом в панировочные сухари. Выложите на разогретую сковороду и обжарьте с двух сторон до готовности (огонь средний, на каждой стороне по 5-7 минут).",
            imageUrl = "https://img.povar.ru/uploads/eb/d9/c2/3d/shnicel_klassicheskii_recept-328820.JPG"
        )),

        IntStep(6, RecipeStep(
        text = "Подайте шницель к столу, прибавив нарезанные овощи или гарнир на ваш вкус. Подробнее: https://povar.ru/recipes/shnicel_klassicheskii_recept-46734.html",
        imageUrl = "https://img.povar.ru/uploads/03/48/6b/d4/shnicel_klassicheskii_recept-328821.JPG",
        ))
        ),
    videoUrl = "https://vkvideo.ru/video-215853179_456240437?ref_domain=yandex-video.naydex.net"
    ),

    Recipe(
    "6",
    "vtoroe",
    "Маульташен",
    "Маульташен - это немецкие пельмени. Они отличаются и формой, фаршем и вкус у них другой. Но этот замечательный рецепт, которым я хочу поделиться, разнообразит привычное блюдо. Давайте приготовим!\n249 ККал",
    "https://img.povar.ru/main-micro/31/a5/e5/a6/maultashen-601463.JPG",
    difficulty = 5,
    timeMinutes = 80,
    ingredients = listOf(
    Ingredient("Мука", "3 стакана"),
    Ingredient("Яичный желток", "5шт"),
    Ingredient("Вода", "80-100мл"),
    Ingredient("Фарш мясной", "500гр"),
    Ingredient("Шпинат", "200гр"),
    Ingredient("Батон", "3ломтика"),
    Ingredient("Молоко", "50мл"),
    Ingredient("Лук репчатыйй", "1шт"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
            text = "Подготовьте необходимые ингредиенты. Замочите в молоке батон. Разморозьте шпинат и отожмите жидкость.",
            imageUrl = "https://img.povar.ru/uploads/f3/07/86/3d/maultashen-601452.JPG",
        )),

        IntStep(2, RecipeStep(
            text = "Сделайте тесто. Смешайте муку с яйцами, водой и солью.",
            imageUrl = "https://img.povar.ru/uploads/8c/b8/38/8f/maultashen-601453.JPG"
        )),

        IntStep(3, RecipeStep(
            text = "Замесите мягкое тесто.",
            imageUrl = "https://img.povar.ru/uploads/95/21/c9/ca/maultashen-601454.JPG"
        )),

        IntStep(4, RecipeStep(
            text = "Отдельно в чаше смешайте: фарш, шпинат, отжатый от молока и измельченный батон, соль, перец, мелко рубленный лук и петрушку.",
            imageUrl = "https://img.povar.ru/uploads/12/0e/ed/08/maultashen-601455.JPG"
        )),

        IntStep(5, RecipeStep(
            text = "перемешиваем",
            imageUrl = "https://img.povar.ru/uploads/f0/2a/b7/5a/maultashen-601456.JPG"
        )),

        IntStep(6, RecipeStep(
            text = "Отделите от теста небольшую часть теста и раскатайте в прямоугольник. Чем длиннее он будет, тем меньшее количество раз придется раскатывать. Разложите на небольшом расстоянии друг от друга фарш.",
            imageUrl = "https://img.povar.ru/uploads/b2/47/a2/bc/maultashen-601457.JPG"
        )),

        IntStep(7, RecipeStep(
            text = "Один из краёв теста смажьте желтком. Заверните на начинку сначала ту часть теста, которая не смазана желтком, потом заверните край смазанный желтком.",
            imageUrl = "https://img.povar.ru/uploads/32/ee/0f/ef/maultashen-601458.JPG"
        )),

        IntStep(8, RecipeStep(
            text = "Расстояние между маульташен прижмите пальцами, чтобы их разделить друг от друга.",
            imageUrl = "https://img.povar.ru/uploads/bc/db/14/92/maultashen-601459.JPG",
        )),
        IntStep(9, RecipeStep(
            text = "Разрежьте и по краям пройдитесь вилкой. Отварите немецкие пельмени в кипящей воде в течение 10-15 минут.",
            imageUrl = "https://img.povar.ru/uploads/ea/0c/df/ac/maultashen-601460.JPG"
        )),
        IntStep(10, RecipeStep(
            text = "Подавать их можно как с бульоном, так и без, со сливочным маслом.",
            imageUrl = "https://img.povar.ru/uploads/0d/c8/d2/09/maultashen-601461.JPG"
        )),
        ),
    videoUrl = "https://vkvideo.ru/video-232563969_456239544?ref_domain=yandex-video.naydex.net"
    ),


    Recipe(
    "7",
    "solati",
    "Вурст-салат",
    "Очень простой в приготовлении салат! По словам автора он относится к швабской кухне и употреблять его можно во всевозможных вариациях: положить на ломоть черного хлеба, есть как самостоятельное блюдо или как гарнир. Упоминается, что вурст-салат вкусно есть с жареной картошкой\n140ккал",
    "https://www.povarenok.ru/data/cache/2013feb/09/01/80456_53737-710x550x.jpg",
    difficulty = 2,
    timeMinutes = 30,
    ingredients = listOf(
        Ingredient("Масло растительное", "2ст ложки"),
        Ingredient("Маринад", "1ст ложка"),
        Ingredient("Уксус 5-6%", "0.5ст"),
        Ingredient("Огурец", "1си ложка"),
        Ingredient("Лук красный", "1шт"),
        Ingredient("Сыр твердый", "100г"),
        Ingredient("Колбаса", "200г"),
        Ingredient("Петрушка", "по вкусу"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "Колбасу порезать брусочками.",
        imageUrl = "https://www.povarenok.ru/data/cache/2013feb/09/01/80451_14776-640x480.jpg",
        )),

        IntStep(2, RecipeStep(
        text = "Так же нарезать сыр.",
        imageUrl = "https://www.povarenok.ru/data/cache/2013feb/09/01/80452_91163-640x480.jpg"
        )),

        IntStep(3, RecipeStep(
        text = "Лук нарезать тонкими полукольцами.",
        imageUrl = "https://www.povarenok.ru/data/cache/2013feb/09/01/80453_56758-640x480.jpg",
        )),

        IntStep(4, RecipeStep(
            text = "Огурчики нарезать тонкими кружочками.",
            imageUrl = "https://www.povarenok.ru/data/cache/2013feb/09/01/80454_84975-640x480.jpg",
        )),

        IntStep(5, RecipeStep(
            text = "Посолить салат (по желанию) и поперчить, добавить петрушку (у меня ее нет) и перемешать.\n" +
                    "Смешать уксус, растительное масло и огуречный маринад и заправить салат. Дать постоять минимум 10 минут и подавать.",
            imageUrl = "https://www.povarenok.ru/data/cache/2013feb/09/01/80455_44981-640x480.jpg",
        )),
        ),
    videoUrl = "https://vkvideo.ru/video-46117626_456265730?ref_domain=yandex-video.naydex.net"
    ),

    Recipe(
    "8",
    "solati",
    "Салат сельдь традиционный",
    "",
    "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_7_max.jpg",
    difficulty = 1,
    timeMinutes = 1,
    ingredients = listOf(
        Ingredient("Селядь", "400гр"),
        Ingredient("Лук", "1шт"),
        Ingredient("Яблоки", "1шт"),
        Ingredient("Огурцы мареновоные", "2шт"),
        Ingredient("Сметана", "200г"),
        Ingredient("Зелень", "По вкусу"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "Подготовить ингредиенты:",
        imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_1_max.jpg",
        )),

        IntStep(2, RecipeStep(
        text = "Селедку нарезать кубиками.Перемешиваю со сметаной.",
        imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_2_max.jpg"
        )),

        IntStep(2, RecipeStep(
            text = "У яблока вырезаю семена и нарезаю кубиками.",
            imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_3_max.jpg"
        )),

        IntStep(2, RecipeStep(
            text = "Добавляю к селедки и перемешиваю.",
            imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_4_max.jpg"
        )),

        IntStep(2, RecipeStep(
            text = "Огурцы и лук нарезаю так же кубиками. Зелень порежу мелко.",
            imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_5_max.jpg"
        )),

        IntStep(3, RecipeStep(
        text = "Добавляем в салат, перчим и хорошо смешиваем все ингредиенты. Салат готов. Приятного аппетита!",
        imageUrl = "https://static.1000.menu/img/content/29595/salat-seld-po-nemecki-tradicionnyi-nemeckii-salat_1542033507_6_max.jpg",
        ))
        ),
    videoUrl = "https://vkvideo.ru/video-207509812_456239027?ref_domain=yandex-video.naydex.net"
    ),

    Recipe(
    "9",
    "solati",
    "картофельный салат с беконом",
    "рецепт тёплого салата из картофеля, маринованных огурцов, копчёных колбасок и бекона. Сытное, вкусное и очень ароматное блюдо",
    "https://www.russianfood.com/dycontent/images_upl/175/big_174248.jpg",
    difficulty = 2,
    timeMinutes = 43,
    ingredients = listOf(
        Ingredient("Картофель", "3-4шт"),
        Ingredient("Колбаски копчёные", "2шт"),
        Ingredient("Бекон нарезанный", "3-4полоски"),
        Ingredient("Лук репчатый красный", "1шт"),
        Ingredient("Огурцы маринованные", "2шт"),
        Ingredient("Уксус белый винный", "2ст л"),
        Ingredient("Сахар коричневый", "1ч л"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
        text = "Подготавливаем продукты. Картофель выбираем с тонкой кожурой.",
        imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174249.jpg",
        )),

        IntStep(2, RecipeStep(
        text = "Картофель тщательно моем под проточной водой (можно мыть щеткой, чтобы кожура была идеально чистой). Нарезаем картофель средними дольками. Заливаем водой и варим на среднем огне 20 минут до",
        imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174250.jpg"
        )),

        IntStep(3, RecipeStep(
            text = "Бекон кладем на сухую разогретую сковороду. Обжариваем бекон на среднем огне, вытапливаем жир.",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174251.jpg"
        )),

        IntStep(4, RecipeStep(
            text = "Очищенный репчатый лук нарезаем перьями.\n" +
                    "Копчёные колбаски нарезаем кружочками.",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174252.jpg"
        )),

        IntStep(5, RecipeStep(
            text = "Маринованные огурцы нарезаем кружочками.\n" +
                    "Петрушку мелко рубим (несколько листиков оставляем для украшения салата).",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174253.jpg"
        )),

        IntStep(6, RecipeStep(
            text = "Когда бекон обжарится, перекладываем его на бумажное полотенце.",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174254.jpg"
        )),

        IntStep(7, RecipeStep(
            text = "В сковороду, в которой жарился бекон, кладем лук и колбаски. Жарим 3 минуты.",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174255.jpg"
        )),

        IntStep(8, RecipeStep(
            text = "Туда же добавляем измельченную зелень, сахар и уксус. Тушим, помешивая, 4 минуты",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174256.jpg"
        )),

        IntStep(9, RecipeStep(
            text = "После этого кладем в сковороду картофель. Аккуратно перемешиваем и выключаем плиту.\n" +
                    "Выкладываем содержимое сковороды в тарелку и ждем 10 минут, пока картофель с колбасками и луком немного",
            imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174257.jpg"
        )),

        IntStep(10, RecipeStep(
        text = "Добавляем маринованные огурцы и нарезанный или раскрошенный жареный бекон.\n" +
                "Солим и перчим тёплый салат по вкусу. Аккуратно перемешиваем.\n" +
                "Украшаем картофельный",
        imageUrl = "https://www.russianfood.com/dycontent/images_upl/175/big_174258.jpg",
        ))
        ),
    videoUrl = "https://vkvideo.ru/video-229933698_456239288?ref_domain=yandex-video.naydex.net"
    ),

    Recipe(
    "10",
    "disert",
    "Апфельштрудель",
    "Этот вкуснейший рулетик с начинкой из изюма и яблок - одно из излюбленных новогодних национальных блюд Австрии. На конкурс \"Новогодние вкусы планеты\".\n378кКал",
    "https://e3.edimdoma.ru/data/recipes/0012/3468/123468-ed4_wide.jpg?1759205978",
    difficulty = 5,
    timeMinutes = 120,
    ingredients = listOf(
        Ingredient("Мука", "150г"),
        Ingredient("Вода", "100мл"),
        Ingredient("Оливковое масло", "26мл"),
        Ingredient("Яйца куринное", "1шт"),
        Ingredient("Хлеб белый", "150гр"),
        Ingredient("Сливочное масло", "3ст л"),
        Ingredient("миндальная мука", "50гр"),
        Ingredient("Яблоки", "1кг"),
        Ingredient("Лимон", "1шт"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
            text = "Изюм ошпарить кипятком, залить ромом и оставить на ночь.",
            imageUrl = "https://e2.edimdoma.ru/data/recipe_steps/0058/6542/586542-ed4_thumb.jpg?1546954415",
        )),

        IntStep(2, RecipeStep(
            text = "Готовим вытяжное тесто. Тут немного стоит рассказать о нюансах. Для приготовления вытяжного теста требуется мука с высоким содержанием глютена. В магазине обратите внимание на значения БЖУ, написанные на упаковке. Белка должно быть 11 г на 100 г продукта. Это и есть показатель того, что тесто из этой муки будет мягким и эластичным.",
            imageUrl = "https://e2.edimdoma.ru/data/recipe_steps/0058/6543/586543-ed4_thumb.jpg?1548846529"
        )),

        IntStep(3, RecipeStep(
            text = "Муку с солью просеять горкой в миску. В углубление вылить яйцо, теплую воду и оливковое масло. Вымесить тесто в форме шара",
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6544/586544-ed4_thumb.jpg?1548846540"
        )),

        IntStep(4, RecipeStep(
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6545/586545-ed4_thumb.jpg?1548846566",
            text = "Стол подпылить мукой и выложить тесто. Теперь его нужно отбить. Просто возьмите шар в руку, поднимите над с столом и от души бросьте. Промесите, повторите. Традиционно это делают до 100 раз. Это также повышает эластичность готового теста. После того как тесто вымешано и отбито, положите его в теплое место (у меня это мультиварка, режим «Мультиповар 35°C») на 40 минут. Полученного количества теста хватит на два больших штруделя."
        )),

        IntStep(5, RecipeStep(
            text = "За это время приготовим начинки. Растопите все сливочное масло. Заранее разогрейте духовку до 190°C. Белую булку нарежьте на кубики. Положите в духовку и приготовьте крутоны. Измельчите крутоны в блендере в крошку. На сковороду вылейте три столовые ложки растопленного масла и поджарьте на нем крошки. Перемешайте с миндальной мукой. Хрустящий слой готов.",
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6548/586548-ed4_thumb.jpg?1546937774"
        )),

        IntStep(6, RecipeStep(
            text = "Яблоки почистите и нарежьте тонкими пластинками. Кладем к яблокам коричневый сахар, ванильный сахар, цедру и сок лимона, перемешиваем руками.",
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6549/586549-ed4_thumb.jpg?1546937827"
        )),

        IntStep(7, RecipeStep(
            text = "Начинается самый ответственный этап. На столе расстелите ткань, подпылите мукой. Выложите тесто на ткань и раскатайте скалкой как можно тоньше.",
            imageUrl = "https://e2.edimdoma.ru/data/recipe_steps/0058/6550/586550-ed4_thumb.jpg?1546937875"
        )),

        IntStep(8, RecipeStep(
            text = "А теперь отложите скалку и начинайте руками растягивать тесто до тех пор, пока через него нельзя будет прочитать газету. Растягивать лучше на костяшках пальцев, чтобы не порвать ногтями.",
            imageUrl = "https://e2.edimdoma.ru/data/recipe_steps/0058/6551/586551-ed4_thumb.jpg?1546937904"
        )),

        IntStep(9, RecipeStep(
            text = "Тесто периодически обильно смазывать растопленным маслом, потому что оно очень быстро высыхает.",
            imageUrl = "https://e3.edimdoma.ru/data/recipe_steps/0058/6552/586552-ed4_thumb.jpg?1546937931"
        )),

        IntStep(10, RecipeStep(
            text = "Растянуть тесто в прямоугольник, положить его узкой стороной к себе. Отступить треть от края и выложить яблочную начинку столбиком по длинной стороне.",
            imageUrl = "https://e3.edimdoma.ru/data/recipe_steps/0058/6553/586553-ed4_thumb.jpg?1546937998",
        )),

        IntStep(11, RecipeStep(
            text = "Насыпать половину жареных крошек. Сверху высыпать изюм.\n",
            imageUrl = "https://e1.edimdoma.ru/data/recipe_steps/0058/6554/586554-ed4_thumb.jpg?1546938029",
        )),

        IntStep(12, RecipeStep(
            text = "С помощью скатерти закрыть сначала узким краем, а потом постепенно свернуть рулетом, смазывая тесто маслом.",
            imageUrl = "https://e1.edimdoma.ru/data/recipe_steps/0058/6555/586555-ed4_thumb.jpg?1546938065",
        )),

        IntStep(13, RecipeStep(
            text = "Готовый штрудель на ткани перенести на противень, покрытый пергаментом, положить швом вниз, придать форму полумесяца, смазать маслом. Поставить в нагретую до 190°C духовку до появления золотистой корочки. У меня это заняло 45 минут.\n",
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6556/586556-ed4_thumb.jpg?1548846780",
        )),

        IntStep(14, RecipeStep(
            text = "Горячий штрудель обильно посыпать сахарной пудрой.  ",
            imageUrl = "https://e0.edimdoma.ru/data/recipe_steps/0058/6557/586557-ed4_thumb.jpg?1548846760",
        ))
    ),
    videoUrl = "https://vkvideo.ru/video-39130500_456239499?ref_domain=yandex-video.naydex.net"
    ),


        Recipe(
    "11",
    "disert",
    "Mедовые пряники \"Лебкухен\"",
    "те самые прякини для нового года\n358 ккал",
    "https://www.povarenok.ru/data/cache/2012oct/21/07/20821_69636-710x550x.jpg",
    difficulty = 3,
    timeMinutes = 75,
    ingredients = listOf(
        Ingredient("Мед", "125гр"),
        Ingredient("Сахар", "50гр"),
        Ingredient("Масло сливочное", "50гр"),
        Ingredient("Сливки", "2ст л"),
        Ingredient("Клюква", "100гр"),
        Ingredient("Мука пшеничная", "250гр"),
        Ingredient("Рарыхлитель теста", "2ч л"),
        Ingredient("Сахар коричневый", "50гр"),
        Ingredient("Шоколад Темный", "200гр"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
            text = "Вот что нам нужно для приготовления.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/06/20812_39615-640x480.jpg",
        )),

        IntStep(2, RecipeStep(
            text = "Смешаем Мед, сахар, масло и сливки в одном ковше.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20813_49558-300x0.jpg"
        )),

        IntStep(3, RecipeStep(
            text = "И поставим на водяную баню до полного растворения масла и сахара.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20814_83865-640x480.jpg"
        )),

        IntStep(4, RecipeStep(
            text = "Получившуюся массу соединим с клюквой и -",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20815_87736-640x480.jpg"
        )),

        IntStep(5, RecipeStep(
            text = " измельчим в миксере.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20816_25798-640x480.jpg"
        )),

        IntStep(6, RecipeStep(
            text = "Вымесим из просеянной муки, разрыхлителя, приправы для Лебкухен (в состав входит корица, мускатный орех, кориандр, гвоздика, имбирь, фенхель, анис, перец, кардамон), медово- клюквенной смеси и коричневого сахара эластичное тесто. Оставим наше тесто на час в холодильнике. В это время приготовим еще два вида печенек, но о них позже.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20817_18965-640x480.jpg"
        )),

        IntStep(7, RecipeStep(
            text = "Итак, тесто охладилось. Раскатаем его в пласт толщиной 4 мм, но можно и потолще. И вырежем формочкой фигурки, у меня были сердечки.\n" +
                    "Да, тесто советую раскатывать между двумя пленками, так как тесто очень липнет.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20818_53356-300x0.jpg"
        )),

        IntStep(8, RecipeStep(
            text = "Разогреваем духовку на 180 градусов и выпекаем наши печеньки 15 минут, ну ни в коем случае не дольше, иначе пересушите, и у нас будут не пряники, а сухарики.",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20819_31045-300x0.jpg"
        )),

        IntStep(9, RecipeStep(
            text = "Растапливаем шоколад на водяной бане и окунаем туда наши прянички, выкладываем на фольгу и ждем, пока шоколад затвердеет.\n" +
                    "Хранить лучше всего в жестяной коробочке.\n" +
                    "На фото печеньки кое-где смазаны шоколадом, дело рук нетерпеливых домочадцев.)))",
            imageUrl = "https://www.povarenok.ru/data/cache/2012oct/21/07/20820_30133-300x0.jpg"
        )),

    ),
    videoUrl = "https://yandex.ru/video/preview/1615509791192291942"
),

        Recipe(
    "12",
    "disert",
    "Творожный штоллен",
    "аналог отечемственого кекса с изюмом\n357 ккал",
    "https://www.povarenok.ru/data/cache/2012nov/14/35/35689_57746-710x550x.jpg",
    difficulty = 4,
    timeMinutes = 120,
    ingredients = listOf(
        Ingredient("Мука", "500гр"),
        Ingredient("Разрехлитель", "15гр"),
        Ingredient("Сахар", "200гр"),
        Ingredient("Ванильный сахар", "8гр"),
        Ingredient("Яйцо куриное", "2шт"),
        Ingredient("Творог", "250гр"),
        Ingredient("изюм", "250гр"),
        Ingredient("Сахарная пудра", "50гр"),
        Ingredient("Масло сливочное", "175гр"),
    ),
    steps = listOf(
        IntStep(1, RecipeStep(
            text = "Смешать: муку, разрыхлитель, сахар, ванильный сахар, соль, ром, миндальный ароматизатор, яйца",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35681_72104-300x0.jpg",
        )),

        IntStep(2, RecipeStep(
            text = "Добавить: мягкое масло, творог,",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35682_43571-300x0.jpg"
        )),

        IntStep(3, RecipeStep(
            text = "Изюм, миндаль.\n" +
                    "\n" +
                    "Все смешать, получится довольно густое тесто",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35683_13883-300x0.jpg"
        )),

        IntStep(4, RecipeStep(
            text = "Духовку включить на 180*С\n" +
                    "\n" +
                    "форму смазать маслом, выложить в нее тесто, хорошо его приминая",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35684_93508-300x0.jpg"
        )),

        IntStep(5, RecipeStep(
            text = "Переворачиваем форму с тестом на лист и ставим в горячую духовку",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35685_75551-300x0.jpg"
        )),

        IntStep(6, RecipeStep(
            text = "Выпекаем около часа, мне хватило 50 минут\n" +
                    "\n" +
                    "готовый штоллен легко выходит из формы\n" +
                    "\n" +
                    "Если нет специальной формы, возьмите форму \"кирпичиком\" для выпечки кексов :)",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35686_13629-300x0.jpg"
        )),

        IntStep(7, RecipeStep(
            text = "Готовый пирог обильно посыпаем сахарной пудрой",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35687_21842-300x0.jpg"
        )),

        IntStep(8, RecipeStep(
            text = "Вот так все просто, можно звать всех к чаю!",
            imageUrl = "https://www.povarenok.ru/data/cache/2012nov/14/35/35688_98100-300x0.jpg"
        )),

    ),
    videoUrl = "https://yandex.ru/video/preview/5517911607156829649"
)


//    Recipe(
//    "10",
//    "disert",
//    "",
//    "",
//    "",
//    difficulty = 1,
//    timeMinutes = 1,
//    ingredients = listOf(
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//        Ingredient("", ""),
//    ),
//    steps = listOf(
//        IntStep(1, RecipeStep(
//            text = "",
//            imageUrl = "",
//        )),
//
//        IntStep(2, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(3, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(4, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(5, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(6, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(7, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(8, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(9, RecipeStep(
//            text = "",
//            imageUrl = ""
//        )),
//
//        IntStep(10, RecipeStep(
//            text = "",
//            imageUrl = "",
//        ))
//    ),
//    videoUrl = ""
//)

)

