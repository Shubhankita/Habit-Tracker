package com.example.habittracker

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class Tasks : AppCompatActivity() {
    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerview : RecyclerView
    private lateinit var userArrayList : ArrayList<TaSkDB>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        val mainsettings = findViewById<ImageView>(R.id.mainsettings)
        mainsettings.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val addTask = findViewById<ImageView>(R.id.addTask)
        addTask.setOnClickListener {
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val bTaskDelete = findViewById<ImageView>(R.id.bTasksDelete)
        bTaskDelete.setOnClickListener {
            val intent = Intent(this, DeleteTask::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
        val bTasksModify = findViewById<ImageView>(R.id.bTasksModify)
        bTasksModify.setOnClickListener{
            val intent =Intent(this,ModifyTask::class.java)
                startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

        userRecyclerview = findViewById(R.id.userlist2)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)

        userArrayList = arrayListOf<TaSkDB>()
        getUserData()
        }
    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Add Task")

        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){


                        val user = userSnapshot.getValue(TaSkDB::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerview.adapter = TaskAdapter(userArrayList)


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
    }










