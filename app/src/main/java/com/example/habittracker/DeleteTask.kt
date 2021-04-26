package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.habittracker.databinding.ActivityDeleteHabitBinding
import com.example.habittracker.databinding.ActivityDeleteTaskBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteTask : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteTaskBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Tasks::class.java)
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
        binding.DeleteTask.setOnClickListener {
            var taskTitle =binding.tDelTaskName.text.toString()
            if(taskTitle.isNotEmpty())
                deteleDataTask(taskTitle)

            else
                Toast.makeText(this,"Please enter task name", Toast.LENGTH_SHORT).show()
        }
    }
    private fun deteleDataTask(taskTitle: String)
    {

        database = FirebaseDatabase.getInstance().getReference("Add Task")
        database.child(taskTitle).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Successfully Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Delete Task",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, Tasks::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()
    }
}