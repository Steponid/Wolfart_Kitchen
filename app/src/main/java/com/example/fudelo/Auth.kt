package com.example.fudelo

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Auth : AppCompatActivity() {
    private lateinit var passSel: ImageView
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        passSel = findViewById(R.id.PassVis2)
        pass = findViewById(R.id.password)

        passSel.setOnClickListener {
            if (passSel.isActivated) {
                passSel.isActivated = false
                pass.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                pass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passSel.isActivated = true
            }
        }

    }
}