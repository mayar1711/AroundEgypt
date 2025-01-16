package com.example.aroundegypt.data.remote.datasource

import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RemoteDataSourceImp @Inject constructor(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getExperiences(): Flow<ExperiencesResponse> = flow {
        emit(apiService.getExperiences())
    }.flowOn(Dispatchers.IO)

    override suspend fun getRecommendedExperiences(): Flow<ExperiencesResponse> = flow {
        emit(apiService.getRecommendedExperiences())
    }.flowOn(Dispatchers.IO)

    override suspend fun getSearchedExperiences(searchText: String): Flow<ExperiencesResponse> =
        flow {
            emit(apiService.getSearchedExperiences(searchText))
        }.flowOn(Dispatchers.IO)

    override suspend fun getSingleExperience(id: String): Flow<SingleExperienceResponse> = flow {
        emit(apiService.getSingleExperience(id))
    }.flowOn(Dispatchers.IO)

    override suspend fun postLikeAnExperience(id: String): LikeAnExperienceResponse {
    return apiService.postLikeAnExperience(id)
}
}