package com.telcel.gsa.sisap.ui.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


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

data class FolioDetailRequest(
    @Json(name="idSolicitud") val idFolio: String,
    @Json(name="idEmpleado") val idEmployee : String
)

@Parcelize
data class FlowControl(
    @Json(name="idControl") val idFlowControl : Int,
    @Json(name="descripcion") val description: String
) : Parcelable

@Parcelize
data class FolioDetail(
    @Json(name="idTsolicitud") val idFolio: Int,
    @Json(name="sistema") val systemString: String,
    @Json(name="descripcion") val description : String,
    @Json(name="estado") val status: String,
    @Json(name="complejidad") val complexity : Int,
    @Json(name="prioridad") val priority : Int,
    @Json(name="fecha") val date : String,
    @Json(name="asignado") val isAssigned : Boolean,
    @Json(name="sox") val sox : String,
    @Json(name="titulo") val title : String,
    @Json(name="controlFlujo") val flowControl: FlowControl,
    @Json(name="estimatedTime") val estimatedTime : Int,
    @Json(name="involucradoSolicitudTOs") val involvedEmployees : List<Employee>
) : Parcelable
