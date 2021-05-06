package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class Incidence(
    @Json(name = "titulo") val title : String,
    @Json(name = "idIncidencia") val idIncidence: String,
    @Json(name = "fcreacion") val creationDate : String,
    @Json(name = "estatusDescripcion") val status : String)

data class IncidentsList(
    @Json(name = "detalleIncidenciasSimple") val incidentsList: List<Incidence>
)