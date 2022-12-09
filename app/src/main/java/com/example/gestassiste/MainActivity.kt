package com.example.gestassiste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gestassiste.fragmentos.Clientes
import com.example.gestassiste.fragmentos.Equipamento
import com.example.gestassiste.Assistencia
import com.example.gestassiste.Consulta

class MainActivity : AppCompatActivity() {
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