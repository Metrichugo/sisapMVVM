package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Json

data class Catalog(
    @Json(name="sistema") val systemCatalog : List<MSystem>,
    @Json(name="complejidad") val complexityCatalog: List<Complexity>,
    @Json(name="tipo") val typeCatalog : List<MType>,
    @Json(name="prioridad") val priorityCatalog : List<MPriority>
)

data class MSystem(
    @Json(name="id_sistema") val idSystem : Int,
    @Json(name="descripcion") val name : String
)

data class Complexity(
    @Json(name="idComplejidad") val idComplexity: Int,
    @Json(name="nombre") val name: String
)

data class MType(
    @Json(name="idTflujogerencia") val idFlow : Int,
    @Json(name="descripcion") val name: String
)

data class MPriority(
    @Json(name="idPrioridad") val idPriority : Int,
    @Json(name="descripcion") val name: String
)

data class CatalogRequest(
    @Json(name="idEmpleado") val idEmployee : String
)

data class ModulesList(
    @Json(name="sistemas") val modules : List<Module>
)

data class Module(
    @Json(name="id_sistema") val idModule : String,
    @Json(name="descripcion") val name : String
)

data class ModulesRequest(
    @Json(name="idSistema") val idSystem : String
)

data class ClassificationRequest(
    @Json(name="idTsolicitud") val idFolio : String,
    @Json(name="idComplejidad") val idComplexity: String,
    @Json(name="idSistema") val idSystem: String,
    @Json(name="idModulo") val idModule: String,
    @Json(name="idTipo") val idType : String,
    @Json(name="idPrioridad") val idPriority: String,
    @Json(name="fechaPrioridad") val priorityDate : String?,
    @Json(name="idJefe") val idBoss: String,
    @Json(name="usuarioLogeadoTO") val loggedUserTO: LoggedUserTO,
    @Json(name="tEmpleadoInvoucradoTOs") val involvedEmployees : List<InvolvedEmployee>
)