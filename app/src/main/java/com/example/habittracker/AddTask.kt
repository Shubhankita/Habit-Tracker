package com.example.habittracker

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_habit.*
import kotlinx.android.synthetic.main.activity_add_task.*
import java.text.SimpleDateFormat
import java.util.*

class AddTask : AppCompatActivity() {
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    lateinit var tTasktitle: EditText
    lateinit var tTaskdesc: EditText
    lateinit var tasktime: Button
    lateinit var addTaskSubmit: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        tTasktitle = findViewById(R.id.tTasktitle)
        tTaskdesc = findViewById(R.id.tTaskdesc)
        tasktime = findViewById(R.id.tasktime)
        addTaskSubmit = findViewById(R.id.addTaskSubmit)




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
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()


        }
        tasktime.setOnClickListener {
            val now = Calendar.getInstance()

            try {
                if (tasktime.text != "Show Dialog") {
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
                    tasktime.text = timeFormat.format(selectedTime.time)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePicker.show()

        }
        addTaskSubmit.setOnClickListener {
            saveTask()

        }

    }
    private fun saveTask(){
        val TaskTitle = tTasktitle.text.toString().trim()
        val TaskDesc = tTaskdesc.text.toString().trim()
        val TaskTime = tasktime.text.toString().trim()
        val TaskcountComplete = 1

        if (TaskTitle.isEmpty()) {
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show()
            return
        }


        val ref1 = FirebaseDatabase.getInstance().getReference("Add Task")

        val task = saveTaskClass(
            TaskTitle,
            TaskDesc,TaskTime,false,TaskcountComplete)

        ref1.child(TaskTitle).setValue(task).addOnCompleteListener {
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show()


        }
        val intent = Intent(this, Tasks::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()


    }


}