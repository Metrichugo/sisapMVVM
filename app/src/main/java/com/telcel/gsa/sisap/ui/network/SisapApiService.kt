package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST

private const val BASE_URL_PRODUCTION = "https://m.sisact.telcel.com:444/sisap-ws/release/sisap/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL_PRODUCTION).build()

interface SisapApiService {
    @POST("lif/incidentsFolio")
    suspend fun getIncidents(): IncidentsList
}

object SisapApi {
    val retrofitService :SisapApiService by lazy {
        retrofit.create(SisapApiService::class.java)
    }
}