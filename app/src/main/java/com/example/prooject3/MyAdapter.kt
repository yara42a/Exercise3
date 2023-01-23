package com.example.prooject3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val taskList: ArrayList<TaskModel>): RecyclerView.Adapter<MyAdapter.myViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(
            R.layout.task_item,
            parent,false)
        return myViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        //ممكن اغير هنا
        val CurrentTask=taskList[position]
        holder.tName.text=CurrentTask.tname
        holder.stat.text=CurrentTask.status.toString()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
    class myViewHolder(itemView:android.view.View): RecyclerView.ViewHolder(itemView){
        val tName: TextView =itemView.findViewById(R.id.taskName)
        val stat: TextView =itemView.findViewById(R.id.status)

    }
}