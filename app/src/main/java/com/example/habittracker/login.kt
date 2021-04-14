package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tEmailAddress
import kotlinx.android.synthetic.main.activity_login.tPassword
import kotlinx.android.synthetic.main.activity_register.*


class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val tloginregister = findViewById<TextView>(R.id.tforgotsignin)
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
            when {
                TextUtils.isEmpty(tEmailAddress.text.toString().trim{ it <= ' '}) ->{
                    Toast.makeText(this@login,"Please Enter Your Email Address",Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(tPassword.text.toString().trim{ it <= ' '}) ->{
                    Toast.makeText(this@login,"Please Enter Password",Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val email:String = tEmailAddress.text.toString().trim{ it <= ' '}
                    val password:String = tPassword.text.toString().trim{ it <= ' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if(task.isSuccessful){

                                    Toast.makeText(this@login,"Sign in Successful",Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, MainMenu::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id",FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                }
                                else{
                                    Toast.makeText(this@login,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                                }
                            }
                    )
                }


            }

        }
    }
}