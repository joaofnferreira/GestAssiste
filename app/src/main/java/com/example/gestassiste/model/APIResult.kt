package com.example.gestassiste.model



import com.google.gson.annotations.SerializedName
import java.util.*


class APIResult (
    @SerializedName("nomeCliente") val nomeCliente: String?,
    @SerializedName("emailCliente") val emailCliente: String?,
    @SerializedName("telefoneCliente") val telefoneCliente: String?,
    @SerializedName("equipamento") val equipamento: String?,
    @SerializedName("modelo") val modelo: String?,
    @SerializedName("~serial") val serial: String?,


    @SerializedName("incioAssistencia") val incioAssistencia: Date?,

    @SerializedName("problemaCliente") val problemaCliente: String?,
    @SerializedName("resolucao") val resolucao: String?,

    @SerializedName("orcamento") val orcamento: String?,
    @SerializedName("dataFim") val dataFim: String?,
    @SerializedName("foto") val foto: String?,



    )


