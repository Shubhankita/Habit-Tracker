package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_self_evaluation.*

class SelfEvaluation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self_evaluation)

        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


            val yes: Button = findViewById(R.id.yes)
            yes.setOnClickListener {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            }

                val no: Button = findViewById(R.id.no)
                no.setOnClickListener {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                }

                    val evaluateButton: Button = findViewById(R.id.evaluate)
                    evaluateButton.setOnClickListener {
                        Toast.makeText(this, "Evaluation Saved", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainMenu::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
                        finish()
                    }


                }
            }









