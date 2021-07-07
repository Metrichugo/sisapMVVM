package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class LoggedUserTO(
    @Json(name="idTEmpleado") val idEmployee: String
)

data class InvolvedEmployee(
    @Json(name="idTempleado") val idEmployee: String,
    @Json(name="esAsignadoLider") var isLeader : Boolean,
    @Json(name="esAsignadoASolicitud") val isAssigned : Boolean
)

data class GenericPostAppResponse(
    @Json(name="resultado") val result : Int
)

data class SolicitudSimpleTO(
    @Json(name="idTSolicitud") val idFolio: String
)