package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AddHabit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)


        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)


        }
    }
}