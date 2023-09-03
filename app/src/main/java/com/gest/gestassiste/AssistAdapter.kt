package com.gest.gestassiste

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adapter para a RecyclerView
class AssistAdapter(private val assistList: ArrayList<AssistModel>) :
    RecyclerView.Adapter<AssistAdapter.ViewHolder>() {

    //declaração
    private lateinit var mListener: onItemClickListener

    //criação da interface para o clickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    //declaração do método do clickListener
    fun setOnItemClickListener(clickListener: onItemClickListener) {
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //fazer o inflate do layout lista_assist.xml
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_assist, parent, false)
        return ViewHolder(itemView, mListener)
    }

    //Vê qual é a assistência e coloca o nome do cliente na textview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAssist = assistList[position]
        holder.tvAssistName.text = currentAssist.nome
    }

    //número de elementos da lista
    override fun getItemCount(): Int {
        return assistList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        //declaração e inicialização
        val tvAssistName: TextView = itemView.findViewById(R.id.tvAssistName)

        init {
            //ao clicar no item na lista...
            itemView.setOnClickListener {
                //passar a posição
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}


