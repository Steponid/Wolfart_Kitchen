package com.example.fudelo.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fudelo.R

class Add : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val typeInput = findViewById<EditText>(R.id.typeInput)
        val descInput = findViewById<EditText>(R.id.descInput)
        val calInput = findViewById<EditText>(R.id.calInput)
        val imgInput = findViewById<EditText>(R.id.imgInput)

        val ingredientsContainer = findViewById<LinearLayout>(R.id.ingredientsContainer)
        val stepsContainer = findViewById<LinearLayout>(R.id.stepsContainer)

        val saveBtn = findViewById<Button>(R.id.saveBtn)

        val prefs = getSharedPreferences("my_recipes", Context.MODE_PRIVATE)
        var recipesSet = prefs.getStringSet("recipes", mutableSetOf())!!.toMutableSet()

        saveBtn.setOnClickListener {
            val name = nameInput.text.toString()
            val type = typeInput.text.toString()
            val desc = descInput.text.toString()
            val cal = calInput.text.toString()
            val img = imgInput.text.toString()

            val ingredients = mutableListOf<String>()
            for (i in 0 until ingredientsContainer.childCount) {
                val txt = (ingredientsContainer.getChildAt(i) as EditText).text.toString()
                if (txt.isNotEmpty()) ingredients.add(txt)
            }

            val steps = mutableListOf<String>()
            for (i in 0 until stepsContainer.childCount) {
                val txt = (stepsContainer.getChildAt(i) as EditText).text.toString()
                if (txt.isNotEmpty()) steps.add(txt)
            }

            val recipeStr = listOf(
                name,
                type,
                desc,
                cal,
                img,
                ingredients.joinToString(";"),
                steps.joinToString(";")
            ).joinToString("|")

            recipesSet.add(recipeStr)
            prefs.edit().putStringSet("recipes", recipesSet).apply()

            Toast.makeText(this, "Рецепт сохранён!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
