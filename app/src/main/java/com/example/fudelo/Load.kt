package com.example.fudelo

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Load : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //немного симуляция загрузки по нармальному грузить данные с БД нужно, но в данный момент базы нет
        object : CountDownTimer(2500, 100){
            override fun onFinish() {
//                по окончанию таймера переходим на экран авторизации
                Log.v("Load", "таймер завершился, переход на авторизацию")
                startActivity(Intent(this@Load, Auth::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()

    }
}