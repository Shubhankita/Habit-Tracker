package com.example.habittracker

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_habit.*
import kotlinx.android.synthetic.main.activity_add_story.*

class AddStory : AppCompatActivity() {

    lateinit var tUsernameStory: EditText
    lateinit var tstorytitle: EditText
    lateinit var tstorydesc: EditText
    lateinit var BstoryUpload: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)


    val mainsettings = findViewById<ImageView>(R.id.mainsettings)
    mainsettings.setOnClickListener {
        val intent = Intent(this, Settings::class.java)
        startActivity(intent)

    }

        //Firebase Database


        tstorytitle = findViewById(R.id.tstorytitle)
        tstorydesc = findViewById(R.id.tstorydesc)
        tUsernameStory=findViewById(R.id.tUsernameStory)
        BstoryUpload = findViewById(R.id.BstoryUpload)

        BstoryUpload.setOnClickListener {
            saveStory()

        }
    }



    @RequiresApi(Build.VERSION_CODES.O)

    private fun saveStory() {
        val tStoryTitle = tstorytitle.text.toString().trim()
        val tStoryDesc = tstorydesc.text.toString().trim()
        val  tUsernameStory = tUsernameStory.text.toString().trim()



        if (tStoryTitle.isEmpty()) {
            Toast.makeText(this, "Please Enter a Title", Toast.LENGTH_SHORT).show()
            return
        }


        val ref = FirebaseDatabase.getInstance().getReference("Add Story")
        val storyID = ref!!.push().key!!
        val story = saveStoryClass(
            storyID,
            tStoryTitle,
            tStoryDesc,
            tUsernameStory)

        ref.child(storyID).setValue(story).addOnCompleteListener {
            Toast.makeText(this, "Story Added", Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this, Stories::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()
    }
}







