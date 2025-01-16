package com.example.aroundegypt.data.model

import com.google.gson.annotations.SerializedName


data class SingleExperienceResponse(
    val meta: Meta,
    @SerializedName("data")
    val experience: Experience,
    val pagination: Pagination? = null
)

data class LikeAnExperienceResponse(
    val meta: Meta,
    val data: Int,
    val pagination: Pagination? = null
)
data class ExperiencesResponse(
    val meta: Meta,
    @SerializedName("data")
    val experiences: List<Experience>,
    val pagination: Pagination? = null
)
