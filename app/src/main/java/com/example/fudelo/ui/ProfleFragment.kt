package com.example.fudelo.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.fudelo.Load
import com.example.fudelo.R
class ProfleFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profle, container, false).apply {
            val exit = findViewById<Button>(R.id.exit)
            exit.setOnClickListener {
                startActivity(Intent(requireContext(), Load::class.java))
            }
        }
    }
}