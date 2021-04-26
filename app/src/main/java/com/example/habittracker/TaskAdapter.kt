package com.example.habittracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val userList2 : ArrayList<TaSkDB>) : RecyclerView.Adapter<TaskAdapter.MyViewHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_card,
            parent,false)
        return MyViewHolder2(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val currentitem = userList2[position]

        holder.taskTitle.text = currentitem.taskTitle
        holder.taskDesc.text = currentitem.taskDesc
        holder.taskTime.text = currentitem.taskTime
    }

    override fun getItemCount(): Int {
        return userList2.size
    }
    class MyViewHolder2(itemView : View) : RecyclerView.ViewHolder(itemView){

        val taskTitle : TextView = itemView.findViewById(R.id.RTaskName)
        val taskDesc : TextView = itemView.findViewById(R.id.RTaskDesc)
        val taskTime : TextView = itemView.findViewById(R.id.RTasktime)


    }
}