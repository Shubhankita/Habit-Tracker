package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)


        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent (this, Settings::class.java)
            startActivity(intent)
        }
        val mainprofile = findViewById<ImageView>(R.id.mainprofile)
        mainprofile.setOnClickListener {
            val intent = Intent (this, Profile::class.java)
            startActivity(intent)
        }
        val bhabits = findViewById<Button>(R.id.bhabits)
        bhabits.setOnClickListener {
            val intent = Intent (this, Habits::class.java)
            startActivity(intent)
        }
        val btasks = findViewById<Button>(R.id.btasks)
        btasks.setOnClickListener {
            val intent = Intent (this, Tasks::class.java)
            startActivity(intent)
        }
        val bstories = findViewById<Button>(R.id.bstories)
        bstories.setOnClickListener {
            val intent = Intent (this, Stories::class.java)
            startActivity(intent)
        }
        val bprogress = findViewById<Button>(R.id.bprogress)
        bprogress.setOnClickListener {
            val intent = Intent (this, Progress::class.java)
            startActivity(intent)
        }
        val bselfeval = findViewById<Button>(R.id.bselfeval)
        bselfeval.setOnClickListener {
            val intent = Intent (this, SelfEvaluation::class.java)
            startActivity(intent)
        }
    }
}