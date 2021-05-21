package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class CommitteeMember(
    @Json(name="id_miembro") val idEmployee : String,
    @Json(name="nombreCompleto") val fullName : String
)

data class CommitteeMembersList(
    @Json(name="nombres") val membersList: List<CommitteeMember>
)

data class CommitteeRequest(
    @Json(name="idSolicitud") val idFolio : String
)