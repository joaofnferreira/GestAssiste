package com.example.gestassiste.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.gestassiste.R


class Frag_Cliente : Fragment() {

    lateinit var cemail: EditText
    lateinit var ctelemovel: EditText
    lateinit var cnome: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_clientes, container, false)

        cnome = view.findViewById(R.id.cnome)
        ctelemovel = view.findViewById(R.id.ctelemovel)
        cemail = view.findViewById(R.id.cemail)

        return view

    }
}


























