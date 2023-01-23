package com.example.prooject3

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddActivity : AppCompatActivity() {
    private lateinit var tname: EditText
    private lateinit var status: RadioGroup
    private lateinit var add: Button
    private lateinit var statusDisplay: TextView
    private var db= Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        tname = findViewById(R.id.taskname)
        add = findViewById(R.id.add)
        status = findViewById(R.id.status)
        statusDisplay = findViewById(R.id.statusDisplay)

        add.setOnClickListener {
            var value = status.checkedRadioButtonId
            var choise = findViewById<RadioButton>(value)
            statusDisplay.text = choise.text

            var Tname=tname.text.toString().trim()
            var display=statusDisplay.text.toString().trim()


            val taskMap= hashMapOf(
                "tname" to Tname,
                "status" to statusDisplay.text.toString().trim(),
            )

            db.collection("Tasks").document().set(taskMap).addOnSuccessListener {
                Toast.makeText(this,"Added", Toast.LENGTH_SHORT).show()
                tname.text.clear()
                statusDisplay.text
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}