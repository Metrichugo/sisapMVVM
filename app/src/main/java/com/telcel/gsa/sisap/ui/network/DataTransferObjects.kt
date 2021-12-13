package com.telcel.gsa.sisap.ui.network

import com.telcel.gsa.sisap.database.DatabaseFolio
import com.telcel.gsa.sisap.domain.Folio

fun FoliosList.asDomainModel(): List<Folio> {
    return foliosList.map {
        Folio(
            daysPassed = it.daysPassed,
            action = it.action,
            title = it.title,
            idFolio = it.idFolio,
            status = it.status,
            creationDate = it.creationDate
        )
    }
}

fun FoliosList.asDataBaseModel(): Array<DatabaseFolio>{
    return foliosList.map{
        DatabaseFolio(
            daysPassed = it.daysPassed,
            action = it.action,
            title = it.title,
            idFolio = it.idFolio,
            status = it.status,
            creationDate = it.creationDate
        )
    }.toTypedArray()
}