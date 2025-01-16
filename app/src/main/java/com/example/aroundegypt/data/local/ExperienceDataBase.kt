package com.example.aroundegypt.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aroundegypt.data.model.Experience
import com.example.aroundegypt.util.Constants
import com.example.aroundegypt.util.Converters

@Database(entities = [Experience::class], version = 2 ,exportSchema = false)
@TypeConverters(Converters::class)

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