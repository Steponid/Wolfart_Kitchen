package com.example.fudelo

import android.content.Intent
import android.database.sqlite.SQLiteProgram
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Auth : AppCompatActivity() {

    //обявление переменных
    private lateinit var passSel: ImageView
    private lateinit var pass: EditText
    private lateinit var ViewGoToReg: View

    private lateinit var emailReg: EditText
    private lateinit var buttonReg: Button

    private lateinit var program: ProgressBar

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

        program = findViewById(R.id.progressBar)

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

        emailReg.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                emailReg.setBackgroundResource(R.drawable.shape_edittext)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        pass.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                pass.setBackgroundResource(R.drawable.shape_edittext)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        buttonReg.setOnClickListener {
            program.visibility = View.VISIBLE

            if (emailReg.text.isNotEmpty() and pass.text.isNotEmpty()) {
                val auth = Firebase.auth
                auth.signInWithEmailAndPassword(emailReg.text.toString(), pass.text.toString())
                    .addOnSuccessListener {
                        startActivity(Intent(this@Auth, ButNav::class.java))
                        finish()
                        program.visibility = View.GONE

                    }
                    .addOnFailureListener {
                        program.visibility = View.GONE
                        emailReg.setBackgroundResource(R.drawable.shape_back_error)
                        pass.setBackgroundResource(R.drawable.shape_back_error)
                        Toast.makeText(baseContext, "проверьте правильность ввода данных", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                program.visibility = View.GONE
                Toast.makeText(baseContext, "поля должны быть заполнены", Toast.LENGTH_SHORT).show()
                emailReg.setBackgroundResource(R.drawable.shape_back_error)
                pass.setBackgroundResource(R.drawable.shape_back_error)
            }
        }




        ViewGoToReg.setOnClickListener {
            startActivity(Intent(this@Auth, Reg::class.java))
        }

    }
}