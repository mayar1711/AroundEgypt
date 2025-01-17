package com.example.aroundegypt.data.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.aroundegypt.data.model.Experience
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class LocalDataSourceImpTest {


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: AppDatabase
    private lateinit var localDataSource: LocalDataSourceImp



    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        localDataSource = LocalDataSourceImp(database.experienceDao())
    }

    @After
    fun tearDown() {
        database.close()
    }


    private val experience = Experience(
        id = "1", title = "Test Experience", likesNo = 0,
        coverPhoto = "",
        description = "",
        viewsNo = 0,
        recommended = 1,
        hasVideo = 1,
        tags = emptyList(),
        city = null,
        tourHtml = "",
        famousFigure = "",
        period = null,
        era = null,
        founded = "",
        detailedDescription = "",
        address = "",
        gmapLocation = null,
        openingHours =null,
        translatedOpeningHours = null,
        startingPrice = 10,
        ticketPrices = emptyList(),
        experienceTips = emptyList(),
        isLiked = true,
        reviews = emptyList(),
        rating = 4,
        reviewsNo = 50,
        audioUrl = "url",
        hasAudio = true
    )


    @Test
    fun insertAllExperiences() = runBlocking {
        val experienceList = listOf(experience)
        `when`(database.experienceDao().getAll()).thenReturn(kotlinx.coroutines.flow.flowOf(experienceList))
        localDataSource.insertAllExperiences(experienceList)
        val result = localDataSource.getAllExperiences().first()
        verify(database.experienceDao()).insertAll(experienceList)
        assert(result.size == 1)
        assert(result[0].title == "Test Experience")

    }

    @Test
    fun deleteAllExperiences() = runBlocking {
        val experienceList = listOf(experience)
        `when`(database.experienceDao().getAll()).thenReturn(kotlinx.coroutines.flow.flowOf(emptyList()))
        localDataSource.insertAllExperiences(experienceList)
        val result = localDataSource.getAllExperiences().first()
        verify(database.experienceDao()).insertAll(experienceList)
        assert(result.isEmpty())
    }

    @Test
    fun updateExperienceLikes() = runBlocking {
        val experienceList = listOf(experience)
        `when`(database.experienceDao().getAll()).thenReturn(kotlinx.coroutines.flow.flowOf(experienceList))
        localDataSource.insertAllExperiences(experienceList)
        localDataSource.updateExperienceLikes("1", 10)
        val result = localDataSource.getAllExperiences().first()
        verify(database.experienceDao()).insertAll(experienceList)
        assert(result[0].likesNo == 10)

    }

}