package com.example.habittracker

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_habit.*
import java.text.SimpleDateFormat
import java.util.*

class AddHabit : AppCompatActivity() {
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    lateinit var ttitle: EditText
    lateinit var tdesc: EditText
    lateinit var month3: RadioButton
    lateinit var month6: RadioButton
    lateinit var month12: RadioButton
    lateinit var mornt: Button
    lateinit var event: Button
    lateinit var addHabitSubmit: Button


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_habit)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, Habits::class.java)
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
        morntime.setOnClickListener {
            val now = Calendar.getInstance()

            try {
                if (morntime.text != "Show Dialog") {
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
                    morntime.text = timeFormat.format(selectedTime.time)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePicker.show()

        }


        eventime.setOnClickListener {
            val now = Calendar.getInstance()

            try {
                if (eventime.text != "Show Dialog") {
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
                    eventime.text = timeFormat.format(selectedTime.time)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timePicker.show()


        }

        //Firebase Database


        ttitle = findViewById(R.id.ttitle)
        tdesc = findViewById(R.id.tdesc)
        month3 = findViewById(R.id.month3)
        month6 = findViewById(R.id.month6)
        month12 = findViewById(R.id.month12)
        mornt = findViewById(R.id.morntime)
        event = findViewById(R.id.eventime)
        addHabitSubmit = findViewById(R.id.addHabitSubmit)

        addHabitSubmit.setOnClickListener {
            saveHabit()

        }
    }



@RequiresApi(Build.VERSION_CODES.O)

private fun saveHabit() {
    val habitTitle = ttitle.text.toString().trim()
    val habitDesc = tdesc.text.toString().trim()
    val mornt = morntime.text.toString().trim()
    val event = eventime.text.toString().trim()
    val countComplete = 1




    if (habitTitle.isEmpty()) {
        Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show()
        return
    }


    val ref = FirebaseDatabase.getInstance().getReference("Add Habit")

    val habit = saveHabitClass(
        habitTitle,
        habitDesc,month3.isChecked,month6.isChecked,month12.isChecked,mornt,event,false,countComplete)

    ref.child(habitTitle).setValue(habit).addOnCompleteListener {
        Toast.makeText(this, "Habit Added", Toast.LENGTH_SHORT).show()
    }
    val intent = Intent(this, Habits::class.java)
    startActivity(intent)
    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    finish()
}
}