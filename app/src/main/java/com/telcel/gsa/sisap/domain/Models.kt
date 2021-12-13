package com.telcel.gsa.sisap.domain

data class Folio(
    val daysPassed : Int,
    val action: String,
    val title: String,
    val idFolio: Int,
    val status: String,
    val creationDate: String
)