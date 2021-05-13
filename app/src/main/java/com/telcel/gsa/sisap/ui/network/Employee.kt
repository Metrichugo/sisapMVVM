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
