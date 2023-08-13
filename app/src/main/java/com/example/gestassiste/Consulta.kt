package com.example.gestassiste


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Consulta : AppCompatActivity() {

    private lateinit var assistReciclerView: RecyclerView
    private lateinit var textViewRecebeDados: TextView
    private lateinit var assistList: ArrayList<AssistModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        assistReciclerView = findViewById(R.id.rvAssist)
        assistReciclerView.layoutManager = LinearLayoutManager(this)
        assistReciclerView.setHasFixedSize(true)
        textViewRecebeDados = findViewById(R.id.textViewRecebeDados)

        assistList = arrayListOf<AssistModel>()

        getAssistData()
    }

    private fun getAssistData(){
        assistReciclerView.visibility = View.GONE
        textViewRecebeDados.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Assist")

        dbRef.addValueEventListener(object  : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                assistList.clear()
                if (snapshot.exists()){
                    for (assistSnap in snapshot.children){
                        val assistData = assistSnap.getValue(AssistModel::class.java)
                        assistList.add(assistData!!)
                    }
                    val mAdapter = AssistAdapter(assistList)
                    assistReciclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object :AssistAdapter.onItemClickListener{

                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@Consulta, AssistDetails::class.java)

                            //put extras
                            intent.putExtra("idassitencia", assistList[position].idassitencia)
                            intent.putExtra("dataassitencia", assistList[position].dataassitencia)
                            intent.putExtra("problemacliente", assistList[position].problemacliente)
                            intent.putExtra("resolucao", assistList[position].resolucao)
                            intent.putExtra("orcamento", assistList[position].orcamento)
                            intent.putExtra("nome", assistList[position].nome)
                            intent.putExtra("telemovel", assistList[position].telemovel)
                            intent.putExtra("email", assistList[position].email)
                            intent.putExtra("equipamento", assistList[position].equipamento)
                            intent.putExtra("modelo", assistList[position].modelo)
                            intent.putExtra("serial", assistList[position].serial)
                            intent.putExtra("imageString", assistList[position].imageString)
                            startActivity(intent)
                        }

                    })

                    assistReciclerView.visibility = View.VISIBLE
                    textViewRecebeDados.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}




