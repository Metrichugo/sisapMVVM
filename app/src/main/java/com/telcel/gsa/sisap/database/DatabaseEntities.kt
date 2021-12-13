package com.telcel.gsa.sisap.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.telcel.gsa.sisap.domain.Folio

@Entity
data class DatabaseFolio constructor(
    val daysPassed: Int,
    val action: String,
    val title: String,
    @PrimaryKey
    val idFolio: Int,
    val status: String,
    val creationDate: String
)

fun List<DatabaseFolio>.asDomainModel(): List<Folio>{
    return map{
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