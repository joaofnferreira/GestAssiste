package com.example.gestassiste.retrofit.service


import com.example.gestassiste.model.APIResult
import com.example.gestassiste.model.Assiste
import retrofit2.Call
import retrofit2.http.*
import java.util.*


interface GestAssisteService {

    @GET("api/Assistencia")
    fun list(): Call<List<Assiste>>

    @GET("api/Assistencia")
    fun listBA(@Header("Authorization") authorization: String): Call<List<Assiste>>


    @GET("api/Assistencia")
    fun listJWT(@Header("Authorization") token: String): Call<List<Assiste>>


  // <--! @FormUrlEncoded
   /// @POST("api/Assistencia")
    ///fun loginJWT(@Field("username") username: String?,
      ///           @Field("password") password: String?): Call<TokenJWT>

  @FormUrlEncoded
   @POST("api/Assistencia")
    fun addAssiste(@Field("NomeCliente") NomeCliente: String?,
                   @Field("EmailCliente") EmailCliente: String?,
                   @Field("TelefoneCliente") TelefoneCliente: String?,
                   @Field("Equipamento") Equipamento: String?,
                   @Field("Modelo") Modelo: String?,
                   @Field("Serial") Serial: String?,
                   @Field("IncioAssistencia") IncioAssistencia: String?,
                   @Field("ProblemaCliente") ProblemaCliente: String?,
                   @Field("Resolucao") Resolucao: String?,
                   @Field("Orcamento") Orcamento: String?,
                   @Field("Datafim") Datafim: String?,
                   @Field("Foto") Foto: String?): Call<APIResult>







    ///--------------------------------------------------------------------------------------

    //@POST("API/notes")
    //fun addNote(@Body note: Note): Call<APIResult>





}