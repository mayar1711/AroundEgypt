package com.example.aroundegypt.data.local

import com.example.aroundegypt.data.model.ExperienceTable
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertAllExperiences(experiences: List<ExperienceTable>)
    fun getAllExperiences(): Flow<List<ExperienceTable>>

}