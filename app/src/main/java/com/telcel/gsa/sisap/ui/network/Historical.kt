package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class HistoricalFolioRequest(
    @Json(name="idSolicitud") val idFolio: String
)

data class HistoricalFolio(
    @Json(name="historico") val historicalList: List<ActionHistorical>
)

data class ActionHistorical(
    @Json(name="comentario") val comment : String,
    @Json(name="idAccion") val idAction : String,
    @Json(name="diasTranscurridos") val daysPassed : Int,
    @Json(name="idTEmpleado") val idEmployee : String,
    @Json(name="fechaAsignacion") val assignmentDate: String,
    @Json(name="idTSolicitudAsociada") val associatedRequest :  Int,
    @Json(name="idTSolicitud") val idFolio : Int,
    @Json(name="idCEstatus") val idStatus : Int,
    @Json(name="idTestatusbysolicitud") val idStatusByRequest : Int,
    @Json(name="estatus") val status : String,
    @Json(name="usuarioModificacion") val modificationUser : String,
    @Json(name="fechaCierre") val deadLine : String,
    @Json(name="bactivaAccion") val activateAction : Boolean,
    @Json(name="bactivaFolioAsociado") val activateAssociatedFolio : Boolean
)