package com.example.fudelo.ui.Adpters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R

data class CategoryItem(
    val idType: String,
    val text: String
)

class CategoryAdapter(
    private val categories: List<CategoryItem>,
    private val onCategorySelected: (CategoryItem) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedCategoryId: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_button_menu, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.bind(category)

        // выбрана ли эта категория
        val isSelected = selectedCategoryId == category.idType

        // Если категория выбрана
        if (isSelected) {
            holder.button.setBackgroundResource(R.drawable.shape_button) // Активный фон
        } else {
            holder.button.setBackgroundResource(R.drawable.shape_button_no) // Неактивный фон
        }

        holder.button.setOnClickListener {
            if (selectedCategoryId != category.idType) {
                // Сохраняем новую выбранную категорию
                selectedCategoryId = category.idType
                notifyDataSetChanged()
                onCategorySelected(category) // Сообщаем фрагменту о выборе
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.button)

        fun bind(category: CategoryItem) {
            // Устанавливаем текст на кнопке
            button.text = category.text
        }
    }

    // Метод для установки выбора по ID
    fun selectCategoryById(id: String?) {
        selectedCategoryId = id
        notifyDataSetChanged()
    }
}