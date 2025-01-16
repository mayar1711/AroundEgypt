package com.example.aroundegypt.data.local

import com.example.aroundegypt.data.model.ExperienceTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val experienceDao: ExperienceDao) : LocalDataSource {

    override suspend fun insertAllExperiences(experiences: List<ExperienceTable>) {
        experienceDao.insertAll(experiences)
    }

    override fun getAllExperiences(): Flow<List<ExperienceTable>> {
        return experienceDao.getAll()
    }
}