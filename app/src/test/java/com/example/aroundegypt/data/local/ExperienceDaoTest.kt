package com.example.aroundegypt.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.aroundegypt.data.model.Experience
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ExperienceDaoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveExperience() = runBlocking {
        val experience = Experience(
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
        database.experienceDao().insertAll(listOf(experience))
        val result = database.experienceDao().getAll().first()
        assertThat(result[0].id, `is`(experience.id))

    }


}
