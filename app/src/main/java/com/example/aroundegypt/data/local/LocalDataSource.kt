package com.example.aroundegypt.data.local

import com.example.aroundegypt.data.model.Experience
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun insertAllExperiences(experiences: List<Experience>)
    fun getAllExperiences(): Flow<List<Experience>>

}