package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json


data class Folio(
    @Json(name="diasTranscurridos") val daysPassed : Int ,
    @Json(name="accion") val action: String,
    @Json(name="titulo") val title: String,
    @Json(name="idTsolicitud") val idFolio: Int,
    @Json(name="nombreEstatus") val status: String,
    @Json(name="fecCreacion") val creationDate: String
)

data class FoliosList(
    @Json(name="detalleSolicitudSimple") val foliosList : List<Folio>
)

data class FoliosRequest(
    @Json(name="idEmpleado") val idEmployee : String
)

