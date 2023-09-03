package com.gest.gestassiste

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

class AssistDetails : AppCompatActivity() {

    //declaração
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

        //inicializar
        initView()
        //colocar os dados nas views
        setValuesToViews()

        //botão de atualizar
        btnUpdate.setOnClickListener {
            //executar a função openUpdateDialog, passando para a mesma id da assistência e o nome do cliente
            openUpdateDialog(
                intent.getStringExtra("idassitencia").toString(),
                intent.getStringExtra("nome").toString(),
            )
        }

        //botão de apagar
        btnDelete.setOnClickListener {
            //executar a função confirmDelete, passando para a mesma o id da assistência a eliminar
            confirmDelete(intent.getStringExtra("idassitencia").toString())
        }
    }

    //confirmação da eliminação
    private fun confirmDelete(id: String) {
        val builder = AlertDialog.Builder(this)
        //título e mensagem a fazer a pergunta
        builder.setTitle("Confirmar eliminação")
        builder.setMessage("Tem certeza que pretende eliminar esta assistência?")

        //caso a resposta seja sim, então usar a função deleteRecord, para apagar a assistência
        builder.setPositiveButton("Sim") { _, _ ->
            deleteRecord(id)
        }

        //caso a resposta seja não, não fazer nada e fechar o dialog
        builder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }

        //criar o mostrar o dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    //função para apagar o registo
    private fun deleteRecord(
        id: String
    ) {
        //referência da BD
        val dbRef = FirebaseDatabase.getInstance().getReference("Assist").child(id)
        //remover
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            //mostrar mensagem após apagar
            Toast.makeText(this, "Assistência eliminada", Toast.LENGTH_LONG).show()

            //passar novamente para a lista após apagar
            val intent = Intent(this, Consulta::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            //caso algo corra mal
            Toast.makeText(this, "Erro ao eliminar: ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    //função para inicializar a view
    private fun initView() {
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

    //função para colocar os dados nas views correspondentes
    private fun setValuesToViews() {
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
                //colocação na view
                apresentaImagem.setImageBitmap(imageBitmap)

                apresentaImagem.visibility = View.VISIBLE
                tvimageString.visibility = View.GONE
            } else {
                apresentaImagem.visibility = View.GONE
            }
        }
    }

    //função para converter de Base64 novamente para Bitmap
    private fun convertBase64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            null
        }
    }

    //função para fazer o update dos dados
    private fun openUpdateDialog(
        id: String,
        nome: String
    ) {

        //criar o dialog
        val mDialog = AlertDialog.Builder(this)

        //fazer o inflate do update_dialog.xml
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        //definir a view no dialog
        mDialog.setView(mDialogView)

        //inicializar as textviews e o botão de update
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

        //colocar o valor atual na janela do update
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

        //definir o título do dialog
        mDialog.setTitle("A atualizar a assistência de $nome")

        //criar e mostrar o dialog
        var alertDialog = mDialog.create()
        alertDialog.show()

        //ao clicar
        btnUpdateData.setOnClickListener {
            //chama a função do update
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
            //mostrar mensagem de sucesso
            Toast.makeText(
                applicationContext,
                "Dados da assistência atualizados",
                Toast.LENGTH_LONG
            ).show()

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

            //fechar o dialog
            alertDialog.dismiss()
        }

    }

    //função para fazer o update dos dados, recebe o id da assistência e os dados que foram atualizados
    private fun updateAssistData(
        id: String,
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
        imageString: String
    ) {
        //referência da BD
        val dbRef = FirebaseDatabase.getInstance().getReference("Assist").child(id)
        //variável com os dados para colocar na BD, no formato do AssistModel
        val assistInfo = AssistModel(
            id,
            dataassitencia,
            problemacliente,
            resolucao,
            orcamento,
            nome,
            telemovel,
            email,
            equipamento,
            modelo,
            serial,
            imageString
        )
        //atualizar os dados na BD
        dbRef.setValue(assistInfo)
    }
}