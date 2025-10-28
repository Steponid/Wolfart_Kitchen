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

class Auth : AppCompatActivity() {

    //обявление переменных
    private lateinit var passSel: ImageView
    private lateinit var pass: EditText
    private lateinit var ViewGoToReg: View

    private lateinit var emailReg: EditText
    private lateinit var buttonReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_auth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //приравнивание обектов к id элемента
        passSel = findViewById(R.id.PassVis2)
        pass = findViewById(R.id.passwordReg)
        ViewGoToReg = findViewById(R.id.ViewGoAuth)
        buttonReg = findViewById(R.id.buttonReg)
        emailReg = findViewById(R.id.emailReg)

        //логика смены видемости пароля
        passSel.setOnClickListener {
            //еслси уже активен то
            if (passSel.isActivated) {
                //меняем на не активный
                passSel.isActivated = false
                //открываем пароль
                pass.transformationMethod = PasswordTransformationMethod.getInstance()
            } else {
                //скрываем пароль и убераем активность кнопки
                pass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passSel.isActivated = true
            }
        }

        buttonReg.setOnClickListener {


            if (pass.text.toString() == "WSR" )
                if (emailReg.text.toString() == "WSR"){
                    startActivity(Intent(this@Auth, ButNav::class.java))
                    finish()
            }
            else{
                Toast.makeText(baseContext, "неправельный логин или пароль", Toast.LENGTH_SHORT).show()
            }


        }

        ViewGoToReg.setOnClickListener {
            startActivity(Intent(this@Auth, Reg::class.java))
        }

    }
}