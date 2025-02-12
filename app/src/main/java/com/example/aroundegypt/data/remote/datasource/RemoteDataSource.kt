package com.example.aroundegypt.data.remote.datasource

import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getExperiences(): Flow<ExperiencesResponse>
    suspend fun getRecommendedExperiences():Flow <ExperiencesResponse>
    suspend fun getSearchedExperiences(searchText: String):Flow <ExperiencesResponse>
    suspend fun getSingleExperience(id: String):Flow<SingleExperienceResponse>
    suspend fun postLikeAnExperience(id: String):LikeAnExperienceResponse

}