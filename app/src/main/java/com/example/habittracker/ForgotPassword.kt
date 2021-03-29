package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val bsubmit = findViewById<TextView>(R.id.bsubmit)
        bsubmit.setOnClickListener {

            Toast.makeText(this,"Email Sent",Toast.LENGTH_SHORT).show()
        }
        val tforgotregister = findViewById<TextView>(R.id.tforgotregister)
        tforgotregister.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
    }
}