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
            holder?.let {
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
                val nomeCliente: TextView = itemView.findViewById(R.id.note_nomeCliente)
                val emailCliente: TextView = itemView.findViewById(R.id.note_emailCliente)
                val telefoneCliente: TextView = itemView.findViewById(R.id.note_telefoneCliente)
                val equipamento: TextView = itemView.findViewById(R.id.note_equipamento)
                val modelo: TextView = itemView.findViewById(R.id.note_modelo)
                val serial: TextView = itemView.findViewById(R.id.note_serial)
                val incioAssistencia: TextView = itemView.findViewById(R.id.note_incioAssistencia)
                val problemaCliente: TextView = itemView.findViewById(R.id.note_problemaCliente)
                val resolucao: TextView = itemView.findViewById(R.id.note_resolucao)
                val orcamento: TextView = itemView.findViewById(R.id.note_orcamento)
                val dataFim: TextView = itemView.findViewById(R.id.note_dataFim)
                val foto: TextView = itemView.findViewById(R.id.note_foto)
                // adicionar os restantes


                nomeCliente.text = assites.nomeCliente
                emailCliente.text = assites.emailCliente
                telefoneCliente.text = assites.telefoneCliente
                equipamento.text = assites.equipamento
                modelo.text = assites.modelo
                serial.text = assites.serial
                incioAssistencia.text = assites.incioAssistencia
                problemaCliente.text = assites.problemaCliente
                resolucao.text = assites.resolucao
                orcamento.text = assites.orcamento
                dataFim.text = assites.dataFim
                foto.text = assites.foto









            }

        }








    }