package com.telcel.gsa.sisap.ui.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    @Json(name="idTEmpleado") val idEmployee: String,
    @Json(name="nombreInvolucrado") val employeeName : String,
    @Json(name="apPatInvolucrado") val employeeLastName : String,
    @Json(name="apMatInvolucrado") val employeeSecondLastName : String
) : Parcelable

data class TeamMember(
    @Json(name="id_jefe") val idEmployee: String,
    @Json(name="nombre") val name: String,
    @Json(name="ap_paterno") val lastName: String,
    @Json(name="ap_materno") val secondLastName: String,
    @Json(name="esAsignadoLider") var isAssignedAsLeader : Boolean,
    @Json(name="esAsignadoASolicitud") var isAssignedAsDeveloper : Boolean,
    @Json(name="perfil") val profile : String?
)

data class TeamMemberList(
    @Json(name="recurso") val TeamMemberList : List<TeamMember>
)

data class MembersTeamRequest(
    @Json(name="idTEmpleado") val idEmployee: String
)

data class AssignmentFolioTO(
    @Json(name="idTSolicitud") val idFolio : String,
    @Json(name="tEmpleadoInvoucradoTOs") val teamMember : List<InvolvedEmployee>,
)

data class AssignmentRequest(
    @Json(name="solicitudSimpleTO") val folio : SolicitudSimpleTO,
    @Json(name="asignaEmpleadoSolicitudTO") val assignmentFolioTO: AssignmentFolioTO,
    @Json(name="usuarioLogeadoTO") val loggedUserTO: LoggedUserTO
)