package com.example.aroundegypt.data.remote.network

import com.example.aroundegypt.data.model.ExperiencesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("experiences")
    suspend fun getExperiences(): ExperiencesResponse


}