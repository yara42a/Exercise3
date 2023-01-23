package com.example.prooject3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private lateinit var Name: EditText
    private lateinit var Phone: EditText
    private lateinit var Email: EditText
    private lateinit var btnSignup: Button
    private lateinit var btn2:TextView
    private var db=Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        Name = findViewById(R.id.PersonName)
        Phone = findViewById(R.id.Phone)
        Email = findViewById(R.id.EmailAddress)
        btnSignup = findViewById(R.id.btn_Signup)
        btn2=findViewById(R.id.Login_btn2)

        btn2.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            var Uname=Name.text.toString().trim()
            var Uphone=Phone.text.toString().trim()
            var Uemail=Email.text.toString().trim()

            val userMap= hashMapOf(
                "name" to Uname,
                "phone" to Uphone,
                "email" to Uemail
            )

            db.collection("Users").document().set(userMap).addOnSuccessListener {
                Toast.makeText(this,"Successfully register",Toast.LENGTH_SHORT).show()
                Name.text.clear()
                Phone.text.clear()
                Email.text.clear()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this,"Failed register",Toast.LENGTH_SHORT).show()
            }
        }
    }
}