package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class DocumentList(
    @Json(name="documentoAsociadoSolicitudTOs") val documentList: List<Document>
)

data class Document(
    @Json(name="id_documento") val idDocument : Int,
    @Json(name="tipo") val type: String,
    @Json(name="fecha") val uploadDate: String,
    @Json(name="documentos") val documentsInformation: DocumentsInformation
)

data class DocumentsInformation(
    @Json(name="nombreDocs") val fileDescription : List<DocumentDescription>
)

data class DocumentDescription(
    @Json(name="nombreOriginal") val name : String,
    @Json(name="nombre") val serialName : String
)

data class DocumentsFolioRequest(
    @Json(name="idSolicitud") val idFolio : String,
    @Json(name="idTEmpleado") val idEmployee: String
)