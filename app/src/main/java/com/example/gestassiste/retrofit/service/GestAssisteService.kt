package com.example.gestassiste.retrofit.service


import com.example.gestassiste.Assistencia
import com.example.gestassiste.model.Assiste
import com.example.gestassiste.model.APIResult
import com.google.gson.annotations.SerializedName
import retrofit2. Call
import retrofit2.http. GET
import retrofit2.http.*


interface GestAssisteService {

    @GET("API/Assistencia")
    fun list(): Call<List<Assiste>>

    @GET("API/Assistencia")
    fun listBA(@Header("Authorization") authorization: String): Call<List<Assiste>>


   // @GET("API/Assistencia")
    //fun listJWT(@Header("Authorization") token: String): Call<List<Assiste>>


  // <--! @FormUrlEncoded
   /// @POST("API/Assistencia")
    ///fun loginJWT(@Field("username") username: String?,
      ///           @Field("password") password: String?): Call<TokenJWT>

  @FormUrlEncoded
   @POST("API/Assistencia")
    fun addAssiste(@Field("nomeCliente") nomeCliente: String?,
                   @Field("emailCliente") emailCliente: String?,
                   @Field("telefoneCliente") telefoneCliente: String?,
                   @Field("equipamento") equipamento: String?,
                   @Field("modelo") modelo: String?,
                   @Field("serial") serial: String?,
                   @Field("incioAssistencia") incioAssistencia: String?,
                   @Field("problemaCliente") problemaCliente: String?,
                   @Field("resolucao") resolucao: String?,
                   @Field("orcamento") orcamento: String?,
                   @Field("datafim") datafim: String?,
                    @Field("foto") foto: String?): Call<APIResult>







    ///--------------------------------------------------------------------------------------

    //@POST("API/notes")
    //fun addNote(@Body note: Note): Call<APIResult>





}