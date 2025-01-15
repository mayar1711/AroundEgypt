package com.example.aroundegypt.data.remote.network

import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("experiences")
    suspend fun getExperiences(): ExperiencesResponse

    @GET("experiences?filter[recommended]=true")
    suspend fun getRecommendedExperiences(): ExperiencesResponse

    @GET("experiences")
    suspend fun getSearchedExperiences(@Query("filter[title]") searchText: String): ExperiencesResponse

    @GET("experiences/id")
    suspend fun getSingleExperience(@Path("id") id: String): SingleExperienceResponse

    @POST("experiences/id/like")
    suspend fun postLikeAnExperience(@Path("id") id: String): LikeAnExperienceResponse

}