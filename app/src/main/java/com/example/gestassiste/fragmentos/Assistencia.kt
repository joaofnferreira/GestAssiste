package com.example.gestassiste.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.gestassiste.AssistModel
import com.example.gestassiste.Assistencia
import com.example.gestassiste.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Assistencia : Fragment() {

    private lateinit var editTextDate: EditText
    private lateinit var cproblema: EditText
    private lateinit var cproblema2: EditText
    private lateinit var corcamento: EditText
    private lateinit var button_save: Button

    private lateinit var cemail: EditText
    private lateinit var ctelemovel: EditText
    private lateinit var cnome: EditText


    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_assistencia, container, false)
        var view2:View = inflater.inflate(R.layout.fragment_clientes, container, false)
        var view3:View = inflater.inflate(R.layout.fragment_equipamento, container, false)

        editTextDate = view.findViewById(R.id.editTextDate)
        cproblema = view.findViewById(R.id.cproblema)
        cproblema2 = view.findViewById(R.id.cproblema2)
        corcamento = view.findViewById(R.id.corcamento)
        button_save = view.findViewById(R.id.button_save)

        cnome = view2.findViewById(R.id.cnome)
        ctelemovel = view2.findViewById(R.id.ctelemovel)
        cemail = view2.findViewById(R.id.cemail)

        dbRef = FirebaseDatabase.getInstance().getReference("Assist")

        button_save.setOnClickListener{
            saveEquipamento()
        }

        return view
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

        if (editTextDate1.isEmpty()){
            editTextDate.error = "Insira o valor"
        }

        if (cproblema1.isEmpty()){
            cproblema.error = "Insira o valor"
        }

        if (cproblema21.isEmpty()){
            cproblema2.error = "Insira o valor"
        }

        if (corcamento1.isEmpty()){
            corcamento.error = "Insira o valor"
        }

        //fragmento cliente
        if (cnome1.isEmpty()){
            cnome.error = "Insira o valor"
        }

        if (ctelemovel1.isEmpty()){
            ctelemovel.error = "Insira o valor"
        }

        if (cemail1.isEmpty()){
            cemail.error = "Insira o valor"
        }

        val assistID = dbRef.push().key!!

        val assist = AssistModel(assistID, editTextDate1, cproblema1, cproblema21, corcamento1, cnome1, ctelemovel1, cemail1)

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


            }.addOnFailureListener {
                //Toast.makeText(this, "Erro: ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}