package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Authentification de l'application
        val valider = findViewById<Button>(R.id.button2)
        valider?.setOnClickListener()
        {
            val pseudo = findViewById<EditText>(R.id.EnterEmail).text.toString()
            val MDP = findViewById<EditText>(R.id.EnterMDP).text.toString()
            if (pseudo == "Test" && MDP == "Test")
            {
                val intent = Intent(this, Page2::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "identifiant invalide", Toast.LENGTH_SHORT).show()
            }
        }
    }
}