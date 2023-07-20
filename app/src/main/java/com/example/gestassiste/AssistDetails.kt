package com.example.gestassiste

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AssistDetails : AppCompatActivity(){

    private lateinit var tvdataassitencia: TextView
    private lateinit var tvproblemacliente: TextView
    private lateinit var tvresolucao: TextView
    private lateinit var tvorcamento: TextView
    private lateinit var tvnome: TextView
    private lateinit var tvtelemovel: TextView
    private lateinit var tvemail: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assist_details)

        tvdataassitencia = findViewById(R.id.tvdataassitencia)
        tvproblemacliente = findViewById(R.id.tvproblemacliente)
        tvresolucao = findViewById(R.id.tvresolucao)
        tvorcamento = findViewById(R.id.tvorcamento)
        tvnome = findViewById(R.id.tvnome)
        tvtelemovel = findViewById(R.id.tvtelemovel)
        tvemail = findViewById(R.id.tvemail)

        initView()
        setValuesToViews()
    }

    private fun  initView(){}

    private fun setValuesToViews(){
        tvdataassitencia.text = intent.getStringExtra("dataassitencia")
        tvproblemacliente.text = intent.getStringExtra("problemacliente")
        tvresolucao.text = intent.getStringExtra("resolucao")
        tvorcamento.text = intent.getStringExtra("orcamento")
        tvnome.text = intent.getStringExtra("nome")
        tvtelemovel.text = intent.getStringExtra("telemovel")
        tvemail.text = intent.getStringExtra("email")
    }
}