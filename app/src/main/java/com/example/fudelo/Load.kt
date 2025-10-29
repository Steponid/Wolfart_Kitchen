package com.example.fudelo

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Load : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_load)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currenUser = Firebase.auth.currentUser
        if (currenUser != null){
            startActivity(Intent(this@Load, Auth::class.java))
            finish()
        }

        object : CountDownTimer(2500, 100){
            override fun onFinish() {
                startActivity(Intent(this@Load, Auth::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
}