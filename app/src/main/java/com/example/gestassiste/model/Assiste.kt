package com.example.gestassiste.model

import com.google.gson.annotations.SerializedName
import java.util.Date


class Assiste(

    @SerializedName("nomeCliente") val nomeCliente: String?,
    @SerializedName("emailCliente") val emailCliente: String?,
    @SerializedName("telefoneCliente") val telefoneCliente: String?,
    @SerializedName("equipamento") val equipamento: String?,
    @SerializedName("modelo") val modelo: String?,
    @SerializedName("serial") val serial: String?,
    @SerializedName("incioAssistencia") val incioAssistencia: String,

    @SerializedName("problemaCliente") val problemaCliente: String?,
    @SerializedName("resolucao") val resolucao: String?,

    @SerializedName("orcamento") val orcamento: String?,
    @SerializedName("datafim") val foto: String?,
    @SerializedName("foto") val dataFim: String?
) {

}



















