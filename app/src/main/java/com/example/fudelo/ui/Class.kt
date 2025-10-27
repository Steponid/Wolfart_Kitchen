package com.example.fudelo.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: String,
    val idType: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val difficulty: Int,
    val timeMinutes: Int,
    val ingredients: List<Ingredient>,
    val steps: List<IntStep>,
    val videoUrl: String? = null,
    var isFavorite: Boolean = false
) : Parcelable

@Parcelize
data class IntStep(
    val index: Int,
    val step: RecipeStep
) : Parcelable

@Parcelize
data class Ingredient(
    val name: String,
    val weight: String
) : Parcelable

@Parcelize
data class RecipeStep(
    val text: String,
    val imageUrl: String? = null,
) : Parcelable
