package com.example.aroundegypt.data.repo

import com.example.aroundegypt.data.model.Experience
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getExperiences(): Flow<ExperiencesResponse>
    suspend fun getRecommendedExperiences():Flow <ExperiencesResponse>
    suspend fun getSearchedExperiences(searchText: String):Flow <ExperiencesResponse>
    suspend fun getSingleExperience(id: String):Flow<SingleExperienceResponse>
    suspend fun postLikeAnExperience(id: String): LikeAnExperienceResponse
    suspend fun insertAllExperiences(experiences: List<Experience>)
    fun getAllExperiences(): Flow<List<Experience>>
    fun checkInternetConnection(): Boolean
    suspend fun deleteAllExperiences()
    suspend fun updateExperienceLikes(id: String, likes: Int)
}