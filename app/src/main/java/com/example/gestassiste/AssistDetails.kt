package com.example.gestassiste

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
    private lateinit var tvequipamento: TextView
    private lateinit var tvmodelo: TextView
    private lateinit var tvserial: TextView
    private lateinit var tvimageString: TextView
    lateinit var apresentaImagem: ImageView

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
                intent.getStringExtra("nome").toString(),
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("idassitencia").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Assist").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Assistência eliminada", Toast.LENGTH_LONG).show()

            val intent = Intent(this,Consulta::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this,"Erro ao eliminar: ${error.message}", Toast.LENGTH_LONG).show()
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

        tvequipamento = findViewById(R.id.tvequipamento)
        tvmodelo = findViewById(R.id.tvmodelo)
        tvserial = findViewById(R.id.tvserial)
        tvimageString = findViewById(R.id.tvimageString)
        apresentaImagem = findViewById(R.id.apresentaImagem)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
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

        tvequipamento.text = intent.getStringExtra("equipamento")
        tvmodelo.text = intent.getStringExtra("modelo")
        tvserial.text = intent.getStringExtra("serial")
        tvimageString.text = intent.getStringExtra("imageString")

        // Receber a string do intent
        val imageString = intent.getStringExtra("imageString")

        if (imageString.isNullOrEmpty()) {
            // Se a imageString estiver vazia
            apresentaImagem.visibility = View.GONE
        } else {
            // Conversão
            val imageBitmap = convertBase64ToBitmap(imageString)


            if (imageBitmap != null) {
                apresentaImagem.setImageBitmap(imageBitmap)
                apresentaImagem.visibility = View.VISIBLE
                tvimageString.visibility = View.GONE
            } else {
                apresentaImagem.visibility = View.GONE
            }
        }
    }

    private fun convertBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }

    private fun openUpdateDialog(
        id: String,
        nome: String
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

        val etequipamento = mDialogView.findViewById<EditText>(R.id.equipamento)
        val etmodelo = mDialogView.findViewById<EditText>(R.id.modelo)
        val etserial = mDialogView.findViewById<EditText>(R.id.serial)

        val etimageString = mDialogView.findViewById<EditText>(R.id.imageString)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //colocar o valor na janela do update
        etdataassitencia.setText(intent.getStringExtra("dataassitencia").toString())
        etproblemacliente.setText(intent.getStringExtra("problemacliente").toString())
        etresolucao.setText(intent.getStringExtra("resolucao").toString())
        etorcamento.setText(intent.getStringExtra("orcamento").toString())

        etnome.setText(intent.getStringExtra("nome").toString())
        ettelemovel.setText(intent.getStringExtra("telemovel").toString())
        etemail.setText(intent.getStringExtra("email").toString())

        etequipamento.setText(intent.getStringExtra("equipamento").toString())
        etmodelo.setText(intent.getStringExtra("modelo").toString())
        etserial.setText(intent.getStringExtra("serial").toString())

        etimageString.setText(intent.getStringExtra("imageString").toString())


        mDialog.setTitle("A atualizar a assistência de $nome")

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
                etemail.text.toString(),

                etequipamento.text.toString(),
                etmodelo.text.toString(),
                etserial.text.toString(),

                etimageString.text.toString()
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

            tvequipamento.text = etequipamento.text.toString()
            tvmodelo.text = etmodelo.text.toString()
            tvserial.text = etserial.text.toString()

            tvimageString.text = etimageString.text.toString()

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
        email: String,
        equipamento: String,
        modelo: String,
        serial: String,
        imageString:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Assist").child(id)
        val assistInfo = AssistModel(id, dataassitencia,problemacliente,resolucao,orcamento,nome,telemovel,email,equipamento,modelo,serial,imageString)
        dbRef.setValue(assistInfo)
    }
}