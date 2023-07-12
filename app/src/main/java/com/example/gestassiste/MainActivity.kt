package com.example.gestassiste

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //// camera


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClick = findViewById<Button>(R.id.criarassistencia)

        buttonClick.setOnClickListener {
            val intent = Intent(this, Assistencia::class.java)
            startActivity(intent)

            val buttonClick = findViewById<Button>(R.id.consultaassiste)
            buttonClick.setOnClickListener {
                val intent = Intent(this, Consulta::class.java)
                startActivity(intent)
            }
        }
    }
    }












