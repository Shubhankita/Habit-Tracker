package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val tregisterlogin = findViewById<TextView>(R.id.tforgotregister)
        tregisterlogin.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }
        val bsignup = findViewById<TextView>(R.id.bsignup)
        bsignup.setOnClickListener {

            Toast.makeText(this,"Account Created! Please Login", Toast.LENGTH_SHORT).show()
        }
    }
}