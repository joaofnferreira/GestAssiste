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

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lista_assist,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAssist = assistList[position]
        //dps mudar para o nome do cliente
        holder.tvAssistName.text = currentAssist.problemacliente
    }

    override fun getItemCount(): Int {
        return assistList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val tvAssistName : TextView = itemView.findViewById(R.id.tvAssistName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}


