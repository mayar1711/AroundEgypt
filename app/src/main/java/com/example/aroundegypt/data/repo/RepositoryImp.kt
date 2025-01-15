package com.example.aroundegypt.data.repo

import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.remote.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getExperiences(): Flow<ExperiencesResponse> {
        return remoteDataSource.getExperiences()
    }

    override suspend fun getRecommendedExperiences(): Flow<ExperiencesResponse> {
        return remoteDataSource.getRecommendedExperiences()
    }

    override suspend fun getSearchedExperiences(searchText: String): Flow<ExperiencesResponse> {
        return remoteDataSource.getSearchedExperiences(searchText)
    }

    override suspend fun getSingleExperience(id: String): Flow<SingleExperienceResponse> {
        return remoteDataSource.getSingleExperience(id)
    }

    override suspend fun postLikeAnExperience(id: String): LikeAnExperienceResponse {
        return remoteDataSource.postLikeAnExperience(id)
    }

}