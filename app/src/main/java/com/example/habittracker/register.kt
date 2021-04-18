package com.example.habittracker
import com.google.android.gms.tasks.OnCompleteListener
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.tforgotsignin
import kotlinx.android.synthetic.main.activity_register.*


class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        tforgotsignin.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }

        bsignup.setOnClickListener {
        when {
            TextUtils.isEmpty(tEmailAddress.text.toString().trim{ it <= ' '}) ->{
                Toast.makeText(this@register,"Please Enter Your Email Address",Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(tPassword.text.toString().trim{ it <= ' '}) ->{
                Toast.makeText(this@register,"Please Enter Password",Toast.LENGTH_SHORT).show()
            }
            else ->{
                val email:String = tEmailAddress.text.toString().trim{ it <= ' '}
                val password:String = tPassword.text.toString().trim{ it <= ' '}

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if(task.isSuccessful){
                                val firebaseUser : FirebaseUser = task.result!!.user!!
                                Toast.makeText(this@register,"Account Created",Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainMenu::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",firebaseUser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(this@register,task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            }


            }            }


        }
}
