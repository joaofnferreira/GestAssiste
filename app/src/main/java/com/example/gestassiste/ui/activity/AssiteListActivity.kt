package com.example.gestassiste.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gestassiste.R
import com.example.gestassiste.R.id.btnGetlistAssiste
import com.example.gestassiste.model.APIResult
import com.example.gestassiste.model.Assiste
import com.example.gestassiste.retrofit.RetrofitInitializer
import com.example.gestassiste.ui.adapter.AssiteListAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class AssiteListActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        val buttonAdd: Button = findViewById(R.id.button)
        buttonAdd.setOnClickListener {
            addDummyAssiste()
        }

        val btnGetlistAssiste: Button = findViewById(btnGetlistAssiste)
        btnGetlistAssiste.setOnClickListener {
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
            "NomeCliente "+ i,
           "EmailCliente"+i,
            "TelefoneCliente"+i,
            "Equipamento" + i,
            "Modelo" + i,
            "Serial" + i,
            "IncioAssistencia" +i,
            "ProblemaCliente" + i,
            "Resolucao" + i,
            "Orcamento" + i,
            "DataFim" + i,
            "Foto" + i

        )

        addAssiste(note) {
            Toast.makeText (this,"Add " + it?.NomeCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.EmailCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.TelefoneCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Equipamento,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Modelo,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Serial,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.IncioAssistencia,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.ProblemaCliente,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Resolucao,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Orcamento,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.DataFim,Toast.LENGTH_SHORT).show()
            Toast.makeText (this,"Add " + it?.Foto,Toast.LENGTH_SHORT).show()
            listAssiste()
        }
    }

    private fun addAssiste(assiste: Assiste, onResult: (APIResult?) -> Unit){
        val call = RetrofitInitializer().gestAssisteService().addAssiste(assiste.NomeCliente,
                                                                        assiste.EmailCliente,
                                                                        assiste.TelefoneCliente,
                                                                        assiste.Equipamento,
                                                                        assiste.Modelo,
                                                                        assiste.Serial,
                                                                        assiste.IncioAssistencia,
                                                                        assiste.ProblemaCliente,
                                                                        assiste.Resolucao,
                                                                        assiste.Orcamento,
                                                                        assiste.DataFim,
                                                                        assiste.Foto
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