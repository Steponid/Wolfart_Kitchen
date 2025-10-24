package com.example.fudelo

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Reg : AppCompatActivity() {
    private lateinit var back: ImageView
    private lateinit var regButton: Button
    private lateinit var goToAuth: View


    //ткстовые поля
    private lateinit var Email: EditText
    private lateinit var Password: EditText
    private lateinit var PassworRep: EditText

    //скрытия пороля
    private lateinit var SelectPassword1: ImageView
    private lateinit var SelectPassword2: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //кнопки и тд
        back = findViewById(R.id.back)
        regButton = findViewById(R.id.buttonReg)
        goToAuth = findViewById(R.id.ViewGoAuth)
        // текст поля
        Email = findViewById(R.id.emailReg)
        Password = findViewById(R.id.passwordReg)
        PassworRep = findViewById(R.id.passwordRepeat)
        //скрытия пароля
        SelectPassword1 = findViewById(R.id.PassVis1)
        SelectPassword2 = findViewById(R.id.PassVis2)

        back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        goToAuth.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        SelectPassword1.setOnClickListener {
            if (SelectPassword1.isActivated) {
                SelectPassword1.isActivated = false
                Password.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                Password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                SelectPassword1.isActivated = true
            }
        }

        SelectPassword2.setOnClickListener {
            if (SelectPassword2.isActivated) {
                SelectPassword2.isActivated = false
                PassworRep.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                PassworRep.transformationMethod = HideReturnsTransformationMethod.getInstance()
                SelectPassword2.isActivated = true
            }
        }

        regButton.setOnClickListener {
            if (Email.text.isNotEmpty() and Password.text.isNotEmpty() and PassworRep.text.isNotEmpty()){
                if (Password.text.toString() == PassworRep.text.toString()){
                    startActivity(Intent(this@Reg, ButNav::class.java))
                    finish()
                }
                else{
                    Toast.makeText(baseContext, "пороли не совподают", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(baseContext, "все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
            }
        }
    }
}