package com.example.fudelo.ui.FragmenMain

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.fudelo.Load
import com.example.fudelo.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class ProfleFragment : Fragment() {



     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profle, container, false).apply {

            val exit = findViewById<Button>(R.id.exit)

            val Email = findViewById<TextView>(R.id.profleTitle)

            Email.text = Firebase.auth.currentUser?.email

            exit.setOnClickListener {
                Firebase.auth.signOut()
                startActivity(Intent(requireContext(), Load::class.java))
            }



        }
    }
}