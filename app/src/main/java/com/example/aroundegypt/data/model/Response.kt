package com.example.aroundegypt.data.model


data class SingleExperienceResponse(
    val meta: Meta,
    val data: Experience,
    val pagination: Pagination? = null
)

data class LikeAnExperienceResponse(
    val meta: Meta,
    val data: Int,
    val pagination: Pagination? = null
)
data class ExperiencesResponse(
    val meta: Meta,
    val data: List<Experience>,
    val pagination: Pagination? = null
)
