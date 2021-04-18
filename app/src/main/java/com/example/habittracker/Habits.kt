package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity

class Habits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habits)

        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }


            val addHabits = findViewById<ImageView>(R.id.addHabits)
            addHabits.setOnClickListener {
                val intent = Intent(this, AddHabit::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            }

        val meditation = findViewById<ImageView>(R.id.meditation)
        meditation.setOnClickListener {
            val intent = Intent(this, AddHabit::class.java)
            startActivity(intent)
        }

        val sleep = findViewById<ImageView>(R.id.sleep)
        sleep.setOnClickListener {
            val intent = Intent(this, AddHabit::class.java)
            startActivity(intent)
        }

        val screentime = findViewById<ImageView>(R.id.screentime)
        screentime.setOnClickListener {
            val intent = Intent(this, AddHabit::class.java)
            startActivity(intent)
        }

        val study = findViewById<ImageView>(R.id.study)
        study.setOnClickListener {
            val intent = Intent(this, AddHabit::class.java)
            startActivity(intent)
        }

        val stress = findViewById<ImageView>(R.id.stress)
        stress.setOnClickListener {
            val intent = Intent(this, AddHabit::class.java)
            startActivity(intent)
        }





        }
    }


