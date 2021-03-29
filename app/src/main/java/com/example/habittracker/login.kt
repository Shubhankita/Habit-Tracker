package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val tloginregister = findViewById<TextView>(R.id.tforgotregister)
        tloginregister.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }
        val tloginforgot = findViewById<TextView>(R.id.tloginForgot)
        tloginforgot.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
        val bsignin = findViewById<Button>(R.id.bsignin)
        bsignin.setOnClickListener {
            if (tEmailAddress.text.toString().equals("ss7975@srmist.edu.in") && tPassword.text.toString().equals("abc123"))
            {
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Wrong Credentials! Try Again", Toast.LENGTH_SHORT).show()
            }

        }
    }
}