package com.example.aroundegypt.data.repo

import android.content.Context
import com.example.aroundegypt.data.local.AppDatabase
import com.example.aroundegypt.data.local.ExperienceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModuleProvider {
    @Provides
    fun provideAppDao(@ApplicationContext ctx: Context): ExperienceDao {
        return AppDatabase.getInstance(ctx).experienceDao()
    }
}