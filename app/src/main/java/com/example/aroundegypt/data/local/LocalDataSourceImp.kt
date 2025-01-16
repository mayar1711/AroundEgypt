package com.example.aroundegypt.data.local

import com.example.aroundegypt.data.model.Experience
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val experienceDao: ExperienceDao) : LocalDataSource {

    override suspend fun insertAllExperiences(experiences: List<Experience>) {
        experienceDao.insertAll(experiences)
    }

    override fun getAllExperiences(): Flow<List<Experience>> {
        return experienceDao.getAll()
    }

    override suspend fun deleteAllExperiences() {
        experienceDao.deleteAll()
    }
}