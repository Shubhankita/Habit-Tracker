package com.example.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_stories.*

class Stories : AppCompatActivity() {
    private lateinit var storydbref : DatabaseReference
    private lateinit var storyRecyclerview : RecyclerView
    private lateinit var storyArrayList : ArrayList<StoryDB>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stories)



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
        val addStory = findViewById<ImageView>(R.id.addStory)
        addStory.setOnClickListener {
            val intent = Intent(this, AddStory2::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

        storyRecyclerview = findViewById(R.id.storyUserList)
        storyRecyclerview.layoutManager = LinearLayoutManager(this)
        storyRecyclerview.setHasFixedSize(true)

        storyArrayList = arrayListOf<StoryDB>()
        getStoryData()




    }
    private fun getStoryData() {

        storydbref = FirebaseDatabase.getInstance().getReference("Add Story")

        storydbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (storySnapshot in snapshot.children){


                        val story = storySnapshot.getValue(StoryDB::class.java)
                        storyArrayList.add(story!!)

                    }

                    storyRecyclerview.adapter = StoryAdapter(storyArrayList)


                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }


}