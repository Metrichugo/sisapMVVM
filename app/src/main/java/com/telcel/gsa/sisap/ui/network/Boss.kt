package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class BossesList(
    @Json(name="jefes_departamento") val bossesList: List<Boss>
)

data class Boss(
    @Json(name="id_jefe") val idEmployee: String,
    @Json(name="nombre") val name : String,
    @Json(name="ap_paterno") val lastName : String,
    @Json(name="ap_materno") val secondLastName : String
)

data class BossesListRequest(
    @Json(name="idTEmpleado") val idEmployee: String
)