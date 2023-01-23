package com.example.prooject3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var phoneNomber: EditText
    private lateinit var btnLognin: Button
    private lateinit var btn2:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phoneNomber = findViewById(R.id.Phone)
        btnLognin = findViewById(R.id.LogIn_btn)
        btn2=findViewById(R.id.registerNowBtn)
        val db=Firebase.firestore
        val ref=db.collection("Users")
        var phoneNum=""
        btn2.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        btnLognin.setOnClickListener {
                db.collection("Users").get().addOnSuccessListener { result ->
            for (document in result) {
                phoneNum=document.get("phone").toString()
                if (phoneNum == phoneNomber.text.toString()) {
                    Toast.makeText(this, "Welcome!", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }

            }
            if (phoneNum.isBlank() && phoneNum != phoneNomber.text.toString()) {
                Toast.makeText(this, "Phone Number not registered", Toast.LENGTH_LONG).show()
            }}
        }
    }
}