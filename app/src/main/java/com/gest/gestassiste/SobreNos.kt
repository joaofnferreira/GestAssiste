package com.gest.gestassiste

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gest.gestassiste.R

class SobreNos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sobre_nos)

        //declaração e inicialização
        val doctrino = findViewById<TextView>(R.id.textView9)
        val playlist = findViewById<TextView>(R.id.textView17)

        //ação de ir para o link
        doctrino.movementMethod = LinkMovementMethod.getInstance()
        playlist.movementMethod = LinkMovementMethod.getInstance()

    }

}