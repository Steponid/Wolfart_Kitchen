package com.example.fudelo.ui

data class Recipe(
    val id: String,
    val idType: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val steps: Map<Int, RecipeStep>,
    var isFavorite: Boolean = false
)

data class RecipeStep(
    val text: String,
    val imageUrl: String? = null,
    val isTitlePage: Boolean = false,
    val isLastPage: Boolean = false
)
