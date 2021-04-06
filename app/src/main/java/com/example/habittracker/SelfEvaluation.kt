package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class SelfEvaluation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_evaluation)
        val evaluateButton: Button = findViewById(R.id.evaluate)
        evaluateButton.setOnClickListener {
            Toast.makeText(this,"Evaluation Saved",Toast.LENGTH_SHORT).show()

        }




                    }
}