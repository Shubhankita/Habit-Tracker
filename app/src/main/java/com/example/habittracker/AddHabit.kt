package com.example.habittracker

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.text.SimpleDateFormat
import java.util.*

class AddHabit : AppCompatActivity() {
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)


        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)

        }
        morntime.setOnClickListener {
            val now = Calendar.getInstance()

            try {
                if(morntime.text != "Show Dialog") {
                    val date = timeFormat.parse(morntime.text.toString())
                    now.time = date
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
            val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                selectedTime.set(Calendar.MINUTE,minute)
                morntime.text = timeFormat.format(selectedTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
            timePicker.show()

        }


            eventime.setOnClickListener {
                val now = Calendar.getInstance()

                try {
                    if(eventime.text != "Show Dialog") {
                        val date = timeFormat.parse(morntime.text.toString())
                        now.time = date
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
                val timePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val selectedTime = Calendar.getInstance()
                    selectedTime.set(Calendar.HOUR_OF_DAY,hourOfDay)
                    selectedTime.set(Calendar.MINUTE,minute)
                    eventime.text = timeFormat.format(selectedTime.time)
                },
                    now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE),false)
                timePicker.show()

            }


        }
    }
