package com.example.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habittracker.databinding.ActivityDeleteHabitBinding

class DeleteHabit : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteHabitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_habit)
    }
}