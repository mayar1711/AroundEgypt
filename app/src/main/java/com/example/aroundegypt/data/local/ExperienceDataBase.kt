package com.example.aroundegypt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aroundegypt.data.model.ExperienceTable
import com.example.aroundegypt.util.Constants

@Database(entities = [ExperienceTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun experienceDao(): ExperienceDao
    companion object{
        private var dbInstance:AppDatabase?=null
        fun getInstance(context: Context):AppDatabase{
            return dbInstance?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                dbInstance =instance
                instance
            }
        }
    }
}