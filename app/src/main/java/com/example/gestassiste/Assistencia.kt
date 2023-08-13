package com.example.gestassiste

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Base64
import java.io.ByteArrayOutputStream


class Assistencia : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private lateinit var cproblema: EditText
    private lateinit var cproblema2: EditText
    private lateinit var corcamento: EditText
    private lateinit var button_save: Button

    private lateinit var cemail: EditText
    private lateinit var ctelemovel: EditText
    private lateinit var cnome: EditText

    lateinit var photo_button: Button
    lateinit var imageView: ImageView

    private lateinit var cequipamento: EditText
    private lateinit var cmodelo: EditText
    private lateinit var cserial: EditText


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assistencia)


        editTextDate = findViewById(R.id.editTextDate)
        cproblema = findViewById(R.id.cproblema)
        cproblema2 = findViewById(R.id.cproblema2)
        corcamento = findViewById(R.id.corcamento)
        button_save = findViewById(R.id.button_save)

        cnome = findViewById(R.id.cnome)
        ctelemovel = findViewById(R.id.ctelemovel)
        cemail = findViewById(R.id.cemail)

        cequipamento = findViewById(R.id.cequipamento)
        cmodelo = findViewById(R.id.cmodelo)
        cserial = findViewById(R.id.cserial)


        photo_button = findViewById(R.id.photo_button)
        imageView = findViewById(R.id.imageView)

        dbRef = FirebaseDatabase.getInstance().getReference("Assist")

        button_save.setOnClickListener {
            saveEquipamento()
        }

        photo_button.setOnClickListener {
            capturePhoto()
        }


    }


    private fun saveEquipamento() {

        //receber valores

        //fragmento assistência
        val editTextDate1 = editTextDate.text.toString()
        val cproblema1 = cproblema.text.toString()
        val cproblema21 = cproblema2.text.toString()
        val corcamento1 = corcamento.text.toString()

        //fragmento cliente
        val cnome1 = cnome.text.toString()
        val ctelemovel1 = ctelemovel.text.toString()
        val cemail1 = cemail.text.toString()

        //fragmento equipamento
        val cequipamento1 = cequipamento.text.toString()
        val cmodelo1 = cmodelo.text.toString()
        val cserial1 = cserial.text.toString()

        if (editTextDate1.isEmpty()) {
            editTextDate.error = "Insira o valor"
            return
        }

        if (cproblema1.isEmpty()) {
            cproblema.error = "Insira o valor"
            return
        }

        if (cproblema21.isEmpty()) {
            cproblema2.error = "Insira o valor"
            return
        }

        if (corcamento1.isEmpty()) {
            corcamento.error = "Insira o valor"
            return
        }

        //fragmento cliente
        if (cnome1.isEmpty()) {
            cnome.error = "Insira o valor"
            return
        }

        if (ctelemovel1.isEmpty()) {
            ctelemovel.error = "Insira o valor"
            return
        }

        if (cemail1.isEmpty()) {
            cemail.error = "Insira o valor"
            return
        }

        //fragmento equipamento
        if (cequipamento1.isEmpty()) {
            cequipamento.error = "Insira o valor"
            return
        }

        if (cmodelo1.isEmpty()) {
            cmodelo.error = "Insira o valor"
            return
        }

        if (cserial1.isEmpty()) {
            cserial.error = "Insira o valor"
            return
        }

        val assistID = dbRef.push().key!!

        //Capturar a imagem
        val imageBitmap = (imageView.drawable as? BitmapDrawable)?.bitmap

        val imageString = if (imageBitmap != null) {
            convertBitmapToBase64(imageBitmap)
        } else {
            ""
        }

        val assist = AssistModel(
            assistID,
            editTextDate1,
            cproblema1,
            cproblema21,
            corcamento1,
            cnome1,
            ctelemovel1,
            cemail1,
            cequipamento1,
            cmodelo1,
            cserial1,
            imageString
        )


        dbRef.child(assistID).setValue(assist)
            .addOnCompleteListener {
                //Toast.makeText(this,"teste",Toast.LENGTH_LONG).show()

                //fragmento assistência
                editTextDate.text.clear()
                cproblema.text.clear()
                cproblema2.text.clear()
                corcamento.text.clear()

                //fragmento cliente
                cnome.text.clear()
                ctelemovel.text.clear()
                cemail.text.clear()

                //fragmento equipamento
                cequipamento.text.clear()
                cmodelo.text.clear()
                cserial.text.clear()


            }.addOnFailureListener {
                //Toast.makeText(this, "Erro: ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    //Função para converter a imagem
    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(cameraIntent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap?
                if (imageBitmap != null) {
                    imageView.setImageBitmap(imageBitmap)
                }
            }
        }



}