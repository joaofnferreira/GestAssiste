package com.example.gestassiste.ui.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestassiste.R
import com.example.gestassiste.model.Assiste





class AssiteListAdapter(private val assistes: List<Assiste>,private val context: Context)
                        : RecyclerView.Adapter<AssiteListAdapter.ViewHolder>() {


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val note = assistes[position]
            holder.let {
                it.bindView(note)
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.assiste_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return assistes.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindView(assites: Assiste) {
                val NomeCliente: TextView = itemView.findViewById(R.id.note_nomeCliente)
                val EmailCliente: TextView = itemView.findViewById(R.id.note_emailCliente)
                val TelefoneCliente: TextView = itemView.findViewById(R.id.note_telefoneCliente)
                val Equipamento: TextView = itemView.findViewById(R.id.note_equipamento)
                val Modelo: TextView = itemView.findViewById(R.id.note_modelo)
                val Serial: TextView = itemView.findViewById(R.id.note_serial)
                val IncioAssistencia: TextView = itemView.findViewById(R.id.note_incioAssistencia)
                val ProblemaCliente: TextView = itemView.findViewById(R.id.note_problemaCliente)
                val Resolucao: TextView = itemView.findViewById(R.id.note_resolucao)
                val Orcamento: TextView = itemView.findViewById(R.id.note_orcamento)
                val DataFim: TextView = itemView.findViewById(R.id.note_dataFim)
                val Foto: TextView = itemView.findViewById(R.id.note_foto)
                // adicionar os restantes


                NomeCliente.text = assites.NomeCliente
                EmailCliente.text = assites.EmailCliente
                TelefoneCliente.text = assites.TelefoneCliente
                Equipamento.text = assites.Equipamento
                Modelo.text = assites.Modelo
                Serial.text = assites.Serial
                IncioAssistencia.text = assites.IncioAssistencia
                ProblemaCliente.text = assites.ProblemaCliente
                Resolucao.text = assites.Resolucao
                Orcamento.text = assites.Orcamento
                DataFim.text = assites.DataFim
                Foto.text = assites.Foto









            }

        }








    }