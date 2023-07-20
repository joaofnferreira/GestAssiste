package com.example.gestassiste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestassiste.R
import com.example.gestassiste.AssistModel

class AssistAdapter (private val assistList: ArrayList<AssistModel>):
    RecyclerView.Adapter<AssistAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_assist,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAssist = assistList[position]
        //dps mudar para o nome do cliente
        holder.tvAssistName.text = currentAssist.problemacliente
    }

    override fun getItemCount(): Int {
        return assistList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvAssistName : TextView = itemView.findViewById(R.id.tvAssistName)
    }
}


