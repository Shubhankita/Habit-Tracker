package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import com.example.habittracker.databinding.ActivityDeleteHabitBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteHabit : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteHabitBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Habits::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            finish()

        }
        binding.DeleteHabit.setOnClickListener {
            var habitTitle =binding.tDelhabitName.text.toString()
            if(habitTitle.isNotEmpty())
                deteleData(habitTitle)

            else
                Toast.makeText(this,"Please enter habit name",Toast.LENGTH_SHORT).show()
        }
    }
    private fun deteleData(habitTitle: String)
    {

        database =FirebaseDatabase.getInstance().getReference("Add Habit")
        database.child(habitTitle).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, Habits::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()
    }
}