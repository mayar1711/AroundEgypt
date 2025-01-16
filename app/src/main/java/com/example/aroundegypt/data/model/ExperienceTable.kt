package com.example.aroundegypt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experiences")
data class ExperienceTable(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val views: Int,
    val likes: Int
)
