package com.example.aroundegypt.data.repo

import com.example.aroundegypt.data.local.LocalDataSource
import com.example.aroundegypt.data.model.Experience
import com.example.aroundegypt.data.model.ExperiencesResponse
import com.example.aroundegypt.data.model.LikeAnExperienceResponse
import com.example.aroundegypt.data.model.SingleExperienceResponse
import com.example.aroundegypt.data.network.NetworkChecker
import com.example.aroundegypt.data.remote.datasource.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource ,
                                        private val localDataSource: LocalDataSource ,
                                        private val networkChecker: NetworkChecker
)
    : Repository {
    override suspend fun getExperiences(): Flow<ExperiencesResponse> {
        return  remoteDataSource.getExperiences()

    }

    override suspend fun getRecommendedExperiences(): Flow<ExperiencesResponse> {
        return  remoteDataSource.getRecommendedExperiences()

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

    override suspend fun insertAllExperiences(experiences: List<Experience>) {
        localDataSource.insertAllExperiences(experiences)
    }

    override fun getAllExperiences(): Flow<List<Experience>> {
        return localDataSource.getAllExperiences()
    }

    override fun checkInternetConnection(): Boolean {
        return networkChecker.hasInternetConnection()
    }

    override suspend fun deleteAllExperiences() {
        localDataSource.deleteAllExperiences()
    }


}