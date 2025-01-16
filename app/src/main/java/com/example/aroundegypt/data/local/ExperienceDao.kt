package com.example.aroundegypt.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aroundegypt.data.model.ExperienceTable
import kotlinx.coroutines.flow.Flow

@Dao
interface ExperienceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(experiences: List<ExperienceTable>)

    @Query("SELECT * FROM experiences")
    fun getAll(): Flow<List<ExperienceTable>>
}