package com.example.gestassiste.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gestassiste.R
import com.example.gestassiste.model.APIResult
import com.example.gestassiste.model.Assiste
import com.example.gestassiste.retrofit.RetrofitInitializer
import com.example.gestassiste.ui.adapter.AssiteListAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class AssiteListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        val buttonAdd: Button = findViewById(R.id.button)
        buttonAdd.setOnClickListener {
            addDummyAssiste()
        }

        val btnGetNotes: Button = findViewById(R.id.btnGetNotes)
        btnGetNotes.setOnClickListener {
            listAssiste()
        }


        val btnClear: Button = findViewById(R.id.btnClear)
        btnClear.setOnClickListener {
            configureList(emptyList())
        }
    }

    private fun listAssiste() {
        val call = RetrofitInitializer().gestAssisteService().list()
        processList(call)
    }





    private fun processList(call: Call<List<Assiste>>) {
        call.enqueue(object : Callback<List<Assiste>?> {
            override fun onResponse(call: Call<List<Assiste>?>?,
                                    response: Response<List<Assiste>?>?) {
                response?.body()?.let {
                    val notes: List<Assiste> = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Assiste>?>?, t: Throwable?) {
                t?.printStackTrace()
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })
    }

    private fun configureList(asssite: List<Assiste>) {
        val recyclerView: RecyclerView = findViewById(R.id.note_list_recyclerview)
        recyclerView.adapter = AssiteListAdapter(asssite, this)
        val layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    fun addDummyAssiste() {
        val i = Random.nextInt(100)

        val note = Assiste(
            "nomeCliente "+ i,
           "emailCliente"+i,
            "telefoneCliente"+i,
            "equipamento" + i,
            "modelo" + i,
            "serial" + i,
            "incioAssistencia" +i,
            "problemaCliente" + i,
            "resolucao" + i,
            "orcamento" + i,
            "dataFim" + i,
            "foto" + i

        )

        addAssiste(note) {
            Toast.makeText (this,"Add " + it?.nomeCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.emailCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.telefoneCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.equipamento,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.modelo,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.serial,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.incioAssistencia,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.problemaCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.resolucao,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.orcamento,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.dataFim,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.foto,Toast.LENGTH_SHORT).show()
            listAssiste()
        }
    }

    private fun addAssiste(assiste: Assiste, onResult: (APIResult?) -> Unit){
        val call = RetrofitInitializer().gestAssisteService().addAssiste(assiste.nomeCliente,
                                                                        assiste.emailCliente,
                                                                        assiste.telefoneCliente,
                                                                        assiste.equipamento,
                                                                        assiste.modelo,
                                                                        assiste.serial,
                                                                        assiste.incioAssistencia,
                                                                        assiste.problemaCliente,
                                                                        assiste.resolucao,
                                                                        assiste.orcamento,
                                                                        assiste.dataFim,
                                                                        assiste.foto
                      )
        call.enqueue(
            object : Callback<APIResult> {
                override fun onFailure(call: Call<APIResult>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse( call: Call<APIResult>, response: Response<APIResult>) {
                    val addAssiste = response.body()
                    onResult(addAssiste)
                }
            }
        )
    }

}