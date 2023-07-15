package com.example.gestassiste.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gestassiste.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Cliente : Fragment() {

    private lateinit var cemail: EditText
    private lateinit var ctelemovel: EditText
    private lateinit var cnome: EditText

    private lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_clientes, container, false)

        cnome = view.findViewById(R.id.cnome)
        ctelemovel = view.findViewById(R.id.ctelemovel)
        cemail = view.findViewById(R.id.cemail)

        //analisar
        dbRef = FirebaseDatabase.getInstance().getReference("Assist")


        return view

    }
}



























