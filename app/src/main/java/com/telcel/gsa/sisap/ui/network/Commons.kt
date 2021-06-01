package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class LoggedUserTO(
    @Json(name="idTEmpleado") val idEmployee: String
)

data class InvolvedEmployee(
    @Json(name="idTempleado") val idEmployee: String,
    @Json(name="esAsignadoLider") val isLeader : String,
    @Json(name="esAsignadoASolicitud") val isAssigned : String
)

data class GenericPostAppResponse(
    @Json(name="resultado") val result : Int
)