package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoryAdapter (private val storyUserList : ArrayList<StoryDB>) : RecyclerView.Adapter<StoryAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.story_card,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val currentitem = storyUserList[position]

        holder.storyTitle.text = currentitem.storyTitle
        holder.storyDesc.text = currentitem.storyDesc
        holder.usernameStory.text = currentitem.usernameStory


    }

    override fun getItemCount(): Int {

        return storyUserList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val storyTitle : TextView = itemView.findViewById(R.id.RStoryTitle)
        val storyDesc : TextView = itemView.findViewById(R.id.RStoryDesc)
        val usernameStory : TextView = itemView.findViewById(R.id.RUsername)


    }

}


