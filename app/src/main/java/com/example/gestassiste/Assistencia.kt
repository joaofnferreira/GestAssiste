package com.example.gestassiste

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Assistencia : AppCompatActivity() {

    private lateinit var editTextDate: EditText
    private lateinit var cproblema: EditText
    private lateinit var cproblema2: EditText
    private lateinit var corcamento: EditText
    private lateinit var button_save: Button

    private lateinit var cemail: EditText
    private lateinit var ctelemovel: EditText
    private lateinit var cnome: EditText

    private lateinit var cserial: EditText
    private lateinit var cserialteste: TextView


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

        //cserialteste = view.findViewById(R.id.cserialteste)

        dbRef = FirebaseDatabase.getInstance().getReference("Assist")

        button_save.setOnClickListener{
            saveEquipamento()
        }



    }


    private fun saveEquipamento(){

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

        //val cserial1 = cserialteste.text.toString()

        if (editTextDate1.isEmpty()){
            editTextDate.error = "Insira o valor"
            return
        }

        if (cproblema1.isEmpty()){
            cproblema.error = "Insira o valor"
            return
        }

        if (cproblema21.isEmpty()){
            cproblema2.error = "Insira o valor"
            return
        }

        if (corcamento1.isEmpty()){
            corcamento.error = "Insira o valor"
            return
        }

        //fragmento cliente
        if (cnome1.isEmpty()){
            cnome.error = "Insira o valor"
            return
        }

        if (ctelemovel1.isEmpty()){
            ctelemovel.error = "Insira o valor"
            return
        }

        if (cemail1.isEmpty()){
            cemail.error = "Insira o valor"
            return
        }

        val assistID = dbRef.push().key!!

        val assist = AssistModel(assistID, editTextDate1, cproblema1, cproblema21, corcamento1, cnome1, ctelemovel1, cemail1)
        //,cserial1

        dbRef.child(assistID).setValue(assist)
            .addOnCompleteListener{
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
                //cserial.text.clear()


            }.addOnFailureListener {
                //Toast.makeText(this, "Erro: ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}