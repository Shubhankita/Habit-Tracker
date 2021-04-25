package com.example.habittracker

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.habittracker.databinding.ActivityModifyHabitBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_habit.*
import kotlinx.android.synthetic.main.activity_modify_habit.*
import java.text.SimpleDateFormat
import java.util.*

class ModifyHabit : AppCompatActivity() {
    var formate = SimpleDateFormat("dd MMM, YYYY", Locale.US)
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    private lateinit var binding: ActivityModifyHabitBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ModifyHabitSubmit.setOnClickListener {
            val habitTitle = binding.tModifytitle.text.toString().trim()
            val habitDesc = binding.tModifydesc.text.toString().trim()
            val mornt = binding.Modifymorntime.text.toString().trim()
            val event = binding.Modifyeventime.text.toString().trim()
            val month3= binding.Modifymonth3.isChecked
            val month6 = binding.Modifymonth6.isChecked
            val month12 = binding.Modifymonth6.isChecked

            updateData(habitTitle,habitDesc,mornt,event,month3,month6,month12)
        }
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
            Modifymorntime.setOnClickListener {

                val now = Calendar.getInstance()

                try {
                    if (Modifymorntime.text != "Show Dialog") {
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
                        Modifymorntime.text = timeFormat.format(selectedTime.time)
                    },
                    now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
                )
                timePicker.show()

            }
            Modifyeventime.setOnClickListener {
                val now = Calendar.getInstance()

                try {
                    if (Modifyeventime.text != "Show Dialog") {
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
                        Modifyeventime.text = timeFormat.format(selectedTime.time)
                    },
                    now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
                )
                timePicker.show()


            }
        }
    private fun updateData(habitTitle: String, habitDesc: String, mornt: String, event: String, month3: Boolean,month6: Boolean,month12: Boolean)
    {
        database = FirebaseDatabase.getInstance().getReference("Add Habit")
        val user = mapOf<String,Any>(
            "habitTitle" to habitTitle,
            "habitDesc" to habitDesc,
            "morningTime" to mornt,
            "eveningTime" to event,
            "dur3month" to month3,
            "dur6month" to month6,
            "dur12month" to month12,
            )
        database.child(habitTitle).updateChildren(user).addOnSuccessListener {
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Habits::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()



        }.addOnFailureListener{

            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()

        }

    }
    }
