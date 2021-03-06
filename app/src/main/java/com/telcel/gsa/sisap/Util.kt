package com.telcel.gsa.sisap

import com.telcel.gsa.sisap.ui.network.FolioDetail
import com.telcel.gsa.sisap.ui.network.TeamMember

fun parseComplexity(folioDetail: FolioDetail) : String{
    return when(folioDetail.complexity) {
        0-> "SIN COMPLEJIDAD"
        1-> "MUY ALTA"
        2-> "ALTA"
        3-> "NORMAL"
        else-> "BAJA"
    }
}

fun parsePriority(folioDetail: FolioDetail) : String{
    return when(folioDetail.priority){
        0-> "SIN PRIORIDAD"
        1-> "MUY ALTA"
        2-> "ALTA"
        3-> "NORMAL"
        4-> "BAJA"
        5-> "URGENTE"
        else-> "-"
    }
}

fun teamMemberFullName(teamMember: TeamMember) : String {
    return teamMember.name.plus(" ").plus(teamMember.lastName).plus(" ").plus(teamMember.secondLastName)
}

enum class LoadingStatus { LOADING, ERROR, DONE}