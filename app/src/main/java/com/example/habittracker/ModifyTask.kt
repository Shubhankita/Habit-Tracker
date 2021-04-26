package com.example.habittracker

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.habittracker.databinding.ActivityModifyHabitBinding
import com.example.habittracker.databinding.ActivityModifyTask2Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_habit.*
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.activity_modify_task2.*
import java.text.SimpleDateFormat
import java.util.*

class ModifyTask : AppCompatActivity() {
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    private lateinit var binding: ActivityModifyTask2Binding
    private lateinit var database : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ModifyTaskSubmit.setOnClickListener {
            val taskTitle = binding.tModifyTasktitle.text.toString().trim()
            val taskDesc = binding.tModifyTaskdesc.text.toString().trim()
            val taskTime = binding.Modifytasktime.text.toString().trim()


            updateDataTask(taskTitle,taskDesc,taskTime)
        }

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Tasks::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            finish()

        }
        Modifytasktime.setOnClickListener {
            val now = Calendar.getInstance()

            try {
                if (Modifytasktime.text != "Show Dialog") {
                    val date = timeFormat.parse(morntime.text.toString())
                    now.time = date
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            val timePicker = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectedTime.set(Calendar.MINUTE, minute)
                    Modifytasktime.text = timeFormat.format(selectedTime.time)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePicker.show()

        }
    }
    private fun updateDataTask(taskTitle: String, taskDesc: String, taskTime: String)
    {
        database = FirebaseDatabase.getInstance().getReference("Add Task")
        val user = mapOf<String,Any>(
            "taskTitle" to taskTitle,
            "taskDesc" to taskDesc,
            "taskTime" to taskTime,

        )
        database.child(taskTitle).updateChildren(user).addOnSuccessListener {
            Toast.makeText(this,"Successfully Updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Tasks::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()



        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update", Toast.LENGTH_SHORT).show()

        }

    }
}