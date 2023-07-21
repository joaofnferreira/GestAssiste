package com.example.gestassiste

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AssistDetails : AppCompatActivity(){


    private lateinit var tvidassitencia: TextView
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

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("idassitencia").toString(),
                intent.getStringExtra("problemacliente").toString(),
            )
        }
    }

    private fun  initView(){
        tvidassitencia = findViewById(R.id.tvidassitencia)
        tvdataassitencia = findViewById(R.id.tvdataassitencia)
        tvproblemacliente = findViewById(R.id.tvproblemacliente)
        tvresolucao = findViewById(R.id.tvresolucao)
        tvorcamento = findViewById(R.id.tvorcamento)
        tvnome = findViewById(R.id.tvnome)
        tvtelemovel = findViewById(R.id.tvtelemovel)
        tvemail = findViewById(R.id.tvemail)

        btnUpdate = findViewById(R.id.btnUpdate)
    }

    private fun setValuesToViews(){
        tvidassitencia.text = intent.getStringExtra("idassitencia")
        tvdataassitencia.text = intent.getStringExtra("dataassitencia")
        tvproblemacliente.text = intent.getStringExtra("problemacliente")
        tvresolucao.text = intent.getStringExtra("resolucao")
        tvorcamento.text = intent.getStringExtra("orcamento")
        tvnome.text = intent.getStringExtra("nome")
        tvtelemovel.text = intent.getStringExtra("telemovel")
        tvemail.text = intent.getStringExtra("email")
    }

    private fun openUpdateDialog(
        id: String,
        problema: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val etdataassitencia = mDialogView.findViewById<EditText>(R.id.dataassitencia)
        val etproblemacliente = mDialogView.findViewById<EditText>(R.id.problemacliente)
        val etresolucao = mDialogView.findViewById<EditText>(R.id.resolucao)
        val etorcamento = mDialogView.findViewById<EditText>(R.id.orcamento)
        val etnome = mDialogView.findViewById<EditText>(R.id.nome)
        val ettelemovel = mDialogView.findViewById<EditText>(R.id.telemovel)
        val etemail = mDialogView.findViewById<EditText>(R.id.email)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //colocar o valor na janela do update
        etdataassitencia.setText(intent.getStringExtra("dataassitencia").toString())
        etproblemacliente.setText(intent.getStringExtra("problemacliente").toString())
        etresolucao.setText(intent.getStringExtra("resolucao").toString())
        etorcamento.setText(intent.getStringExtra("orcamento").toString())
        etnome.setText(intent.getStringExtra("nome").toString())
        ettelemovel.setText(intent.getStringExtra("telemovel").toString())
        etemail.setText(intent.getStringExtra("email").toString())

        //qd tivermos o nome, mudar isto para nome
        mDialog.setTitle("A atualizar a assistência de $problema")

        var alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener{
            updateAssistData(
                id,
                //receber o valor que foi atualizado
                etdataassitencia.text.toString(),
                etproblemacliente.text.toString(),
                etresolucao.text.toString(),
                etorcamento.text.toString(),
                etnome.text.toString(),
                ettelemovel.text.toString(),
                etemail.text.toString()
            )
            Toast.makeText(applicationContext,"Dados da assistência atualizados", Toast.LENGTH_LONG).show()

            //atualizar no campo dos detalhes o valor referente
            tvdataassitencia.text = etdataassitencia.text.toString()
            tvproblemacliente.text = etproblemacliente.text.toString()
            tvresolucao.text = etresolucao.text.toString()
            tvorcamento.text = etorcamento.text.toString()
            tvnome.text = etnome.text.toString()
            tvtelemovel.text = ettelemovel.text.toString()
            tvemail.text = etemail.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateAssistData(
        id:String,
        dataassitencia: String,
        problemacliente: String,
        resolucao: String,
        orcamento: String,
        nome: String,
        telemovel: String,
        email: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Assist").child(id)
        val assistInfo = AssistModel(id, dataassitencia,problemacliente,resolucao,orcamento,nome,telemovel,email)
        dbRef.setValue(assistInfo)
    }
}