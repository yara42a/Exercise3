package com.example.prooject3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var TaskRecyclerview: RecyclerView
    private var db=Firebase.firestore
    private lateinit var TaskArrayList:ArrayList<TaskModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var Add=findViewById<ImageButton>(R.id.add)
        TaskRecyclerview=findViewById(R.id.taskList)
        TaskRecyclerview.layoutManager= LinearLayoutManager(this)
        //TaskRecyclerview.setHasFixedSize(true)

        TaskArrayList= arrayListOf()
        db= FirebaseFirestore.getInstance()

        Add.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        db.collection("Tasks").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(task in it.documents){
                    val t:TaskModel? =task.toObject(TaskModel::class.java)
                    if(t != null){
                        TaskArrayList.add(t)
                    }
                }
                TaskRecyclerview.adapter=MyAdapter(TaskArrayList)
            }
        }
    }
}