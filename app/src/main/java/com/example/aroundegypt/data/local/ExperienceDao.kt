package com.example.aroundegypt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundegypt.data.model.Experience
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(experiences: List<Experience>)

    @Query("SELECT * FROM experiences")
    fun getAll(): Flow<List<Experience>>
}