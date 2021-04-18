package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_register.*

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        val bsubmit = findViewById<TextView>(R.id.bsubmit)
        bsubmit.setOnClickListener {
            val email:String = tEmail.text.toString().trim{ it <= ' '}
            if(email.isEmpty())
            {
                Toast.makeText(this@ForgotPassword,"Please enter email address",Toast.LENGTH_SHORT).show()
            }
            else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this@ForgotPassword,"Email Sent Successfully",Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            else{
                                Toast.makeText(this@ForgotPassword,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                            }
                        }}
        }
        val tforgotregister = findViewById<TextView>(R.id.tforgotsignin)
        tforgotregister.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }
}