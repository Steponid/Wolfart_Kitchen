package com.example.fudelo.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fudelo.R

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterCatalog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.RecyCatalog)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogItems = listOf(
            catalog(1, "Пункт 1", R.drawable.ic_launcher_foreground),
            catalog(2, "Пункт 2", R.drawable.ic_launcher_foreground),
            catalog(3, "Пункт 3", R.drawable.ic_launcher_foreground)
        )

        adapter = AdapterCatalog(catalogItems) { item ->

        }

        recyclerView.adapter = adapter
    }


}