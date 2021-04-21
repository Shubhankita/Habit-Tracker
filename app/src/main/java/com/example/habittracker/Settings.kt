package com.example.habittracker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.tUsername
import java.io.File


class Settings : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        val user = Firebase.auth.currentUser

        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            tUsername.text = Editable.Factory.getInstance().newEditable(name)
            Glide.with(this).load(user?.photoUrl).placeholder(R.drawable.usercircle).into(mainprofile)

        }



        val update = findViewById<Button>(R.id.updateinfo)
        update.setOnClickListener {
            Toast.makeText(this, "Information Updated", Toast.LENGTH_SHORT).show()


        }


        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            GoogleSignIn.getClient(
                this,
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            ).signOut()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@Settings, login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}