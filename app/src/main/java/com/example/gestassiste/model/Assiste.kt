package com.example.gestassiste.model

import com.google.gson.annotations.SerializedName
import java.util.*


class Assiste(

    @SerializedName("NomeCliente") val NomeCliente: String?,
    @SerializedName("EmailCliente") val EmailCliente: String?,
    @SerializedName("TelefoneCliente") val TelefoneCliente: String?,
    @SerializedName("Equipamento") val Equipamento: String?,
    @SerializedName("Modelo") val Modelo: String?,
    @SerializedName("Serial") val Serial: String?,
    @SerializedName("IncioAssistencia") val IncioAssistencia: String?,
    @SerializedName("ProblemaCliente") val ProblemaCliente: String?,
    @SerializedName("Resolucao") val Resolucao: String?,
    @SerializedName("Orcamento") val Orcamento: String?,



    @SerializedName("DataFim") val DataFim: String?,
    @SerializedName("Foto") val Foto: String?,
) {

}



















