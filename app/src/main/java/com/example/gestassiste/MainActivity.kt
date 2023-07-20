package com.example.gestassiste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        val buttonClick = findViewById<Button>(R.id.criarassistencia)

        buttonClick.setOnClickListener {
            val intent = Intent(this, Assistencia::class.java)
            startActivity(intent)
        }

        val buttonClick1 = findViewById<Button>(R.id.consultaassiste)

        buttonClick1.setOnClickListener {
            val intent = Intent(this, Consulta::class.java)
            startActivity(intent)
        }
    }
    }












