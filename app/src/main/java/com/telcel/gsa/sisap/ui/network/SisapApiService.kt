package com.telcel.gsa.sisap.ui.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL_PRODUCTION = "https://m.sisact.telcel.com:444/sisap-ws/release/sisap/"
private const val BASE_URL_DEVELOPMENT = "http://10.191.209.43:9080/sisap-ws/release/sisap/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL_DEVELOPMENT).build()

interface SisapApiService {

    @Headers("Content-Type: application/json; charset=utf-8",
            "messageUUID: 356904090414635")
    @POST("lif/incidentsFolio")
    suspend fun getIncidents(@Body incidenceRequest: IncidenceRequest): IncidentsList

    @Headers("Content-Type: application/json; charset=utf-8",
        "messageUUID: 356904090414635")
    @POST("rpa/pendingAction")
    suspend fun getFolios(@Body foliosRequest: FoliosRequest) : FoliosList
}

object SisapApi {
    val retrofitService :SisapApiService by lazy {
        retrofit.create(SisapApiService::class.java)
    }
}