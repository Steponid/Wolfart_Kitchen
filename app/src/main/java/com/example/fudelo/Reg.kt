package com.example.fudelo

import android.content.Intent
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
import androidx.core.widget.addTextChangedListener
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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


    private lateinit var ProgressBar: ProgressBar

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

        ProgressBar = findViewById(R.id.progressBar)

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

        Email.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Email.setBackgroundResource(R.drawable.shape_edittext)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        Password.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                Password.setBackgroundResource(R.drawable.shape_edittext)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        PassworRep.addTextChangedListener( object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                PassworRep.setBackgroundResource(R.drawable.shape_edittext)

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        regButton.setOnClickListener {
            ProgressBar.visibility = View.VISIBLE
            if (Email.text.isNotEmpty() and Password.text.isNotEmpty() and PassworRep.text.isNotEmpty()){
                if (Email.text.contains("@")){
                    if (Password.text.length == 8){
                        if (Password.text.toString() == PassworRep.text.toString()){
                            Firebase.auth.createUserWithEmailAndPassword(Email.text.toString(), Password.text.toString())
                                .addOnSuccessListener {
                                    Toast.makeText(baseContext, "Учетная запясь ${Email.text} создана", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@Reg, ButNav::class.java))
                                    finish()
                                    ProgressBar.visibility = View.GONE
                                }
                                .addOnFailureListener {
                                    Toast.makeText(baseContext, "Ошибка", Toast.LENGTH_SHORT).show()
                                    ProgressBar.visibility = View.GONE
                                }
                        }
                        else{
                            Toast.makeText(baseContext, "пороли не совподают", Toast.LENGTH_SHORT).show()
                            Password.setBackgroundResource(R.drawable.shape_back_error)
                            PassworRep.setBackgroundResource(R.drawable.shape_back_error)
                            ProgressBar.visibility = View.GONE
                        }
                    }
                    else{
                        Toast.makeText(baseContext, "пороль должен быть от 8 символов", Toast.LENGTH_SHORT).show()
                        Password.setBackgroundResource(R.drawable.shape_back_error)
                        PassworRep.setBackgroundResource(R.drawable.shape_back_error)
                        ProgressBar.visibility = View.GONE
                    }
                }
                else{
                    Toast.makeText(baseContext, "укажите домен почты", Toast.LENGTH_SHORT).show()
                    Email.setBackgroundResource(R.drawable.shape_back_error)
                    ProgressBar.visibility = View.GONE
                }
            } else {
                Toast.makeText(baseContext, "все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
                Email.setBackgroundResource(R.drawable.shape_back_error)
                Password.setBackgroundResource(R.drawable.shape_back_error)
                PassworRep.setBackgroundResource(R.drawable.shape_back_error)
                ProgressBar.visibility = View.GONE
            }
        }
    }
}