package com.example.fudelo.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R

 data class catalog(
     val id: Int,
     val text: String,
     val img: Int
 )
class AdapterCatalog(
    private val catalogList: List<catalog>,
    private val onItemClick: (catalog) -> Unit
) : RecyclerView.Adapter<AdapterCatalog.CatalogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_catalog_rec, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = catalogList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = catalogList.size

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView9)
//        private val imageView: ImageView = itemView.findViewById(R.id.imageViewCatalogItem)

        fun bind(catalogItem: catalog) {
            textView.text = catalogItem.text
//            imageView.setImageResource(catalogItem.img)
        }
    }
}