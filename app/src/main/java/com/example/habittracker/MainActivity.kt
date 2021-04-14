package com.example.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent (this, login::class.java)
            startActivity(intent)
        }

        val tregister = findViewById<TextView>(R.id.tforgotsignin)
        tregister.setOnClickListener {
            val intent = Intent (this, register::class.java)
            startActivity(intent)
        }

    }
}
