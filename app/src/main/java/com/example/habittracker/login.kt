package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.tEmailAddress
import kotlinx.android.synthetic.main.activity_login.tPassword



class login : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Firebase Auth instance
        mAuth = FirebaseAuth.getInstance()

        tgooglesignin.setOnClickListener {
            signIn()
        }
        loging.setOnClickListener{
            signIn()
        }
        val tloginregister = findViewById<TextView>(R.id.tforgotsignin)
        tloginregister.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
        val tloginforgot = findViewById<TextView>(R.id.tloginForgot)
        tloginforgot.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
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
                                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
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

private fun signIn() {
    val signInIntent = googleSignInClient.signInIntent
    startActivityForResult(signInIntent, RC_SIGN_IN)
}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val exception = task.exception
        if (task.isSuccessful) {
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("SignInActivity", "Google sign in failed", e)
            }
        } else {
            Log.w("SignInActivity", exception.toString())
        }
    }
}

private fun firebaseAuthWithGoogle(idToken: String) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("SignInActivity", "signInWithCredential:success")
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                finish()
            } else {
                // If sign in fails, display a message to the user.
                Log.d("SignInActivity", "signInWithCredential:failure")
            }
        }
}

}

