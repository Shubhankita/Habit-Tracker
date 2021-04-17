package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class AddHabit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)


        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

            val mainprofile = findViewById<ImageView>(R.id.mainprofile)
            mainprofile.setOnClickListener {
                val intent = Intent (this, Profile::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }
        }
        val addHabitSubmit: Button = findViewById(R.id.addHabitSubmit)
        addHabitSubmit.setOnClickListener {
            Toast.makeText(this, "Habit Added", Toast.LENGTH_SHORT).show()
        }
    }
}