package com.gest.gestassiste

data class AssistModel (
        //assistência
        val idassitencia: String? = null,
        val dataassitencia: String? = null,
        val problemacliente: String? = null,
        val resolucao: String? = null,
        val orcamento: String? = null,
        //cliente
        val nome: String? = null,
        val telemovel: String? = null,
        val email: String? = null,
        //equipamento
        val equipamento: String? = null,
        val modelo: String? = null,
        val serial: String? = null,
        var imageString: String? = null
)





