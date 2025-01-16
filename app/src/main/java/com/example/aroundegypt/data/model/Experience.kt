package com.example.aroundegypt.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Meta(
    val code: Int,
    val errors: List<String>?
)
@Entity(tableName = "experiences")
data class Experience(
    @PrimaryKey val id: String,
    val title: String,
    @SerializedName("cover_photo") val coverPhoto: String,
    val description: String,
    @SerializedName("views_no") val viewsNo: Int,
    @SerializedName("likes_no") val likesNo: Int,
    val recommended: Int,
    @SerializedName("has_video") val hasVideo: Int? = null,
    val tags: List<Tag>? = null,
    val city: City? = null,
    @SerializedName("tour_html") val tourHtml: String? = null,
    @SerializedName("famous_figure") val famousFigure: String? = null,
    val period: Period? = null,
    val era: Era? = null,
    val founded: String? = null,
    @SerializedName("detailed_description") val detailedDescription: String? = null,
    val address: String? = null,
    @SerializedName("gmap_location") val gmapLocation: GmapLocation? = null,
    @SerializedName("opening_hours") val openingHours: OpeningHours? = null,
    @SerializedName("translated_opening_hours") val translatedOpeningHours: TranslatedOpeningHours? = null,
    @SerializedName("starting_price") val startingPrice: Int? = null,
    @SerializedName("ticket_prices") val ticketPrices: List<TicketPrice>? = null,
    @SerializedName("experience_tips") val experienceTips: List<String>? = null,
    @SerializedName("is_liked") val isLiked: Boolean? = null,
    val reviews: List<Review>? = null,
    val rating: Int? = null,
    @SerializedName("reviews_no")val reviewsNo: Int? = null,
    @SerializedName("audio_url") val audioUrl: String? = null,
    @SerializedName("has_audio") val hasAudio: Boolean? = null
)

data class Tag(
    val id: Int,
    val name: String,
    val disable: String? = null,
    @SerializedName("top_pick") val topPick: Int
)

data class City(
    val id: Int,
    val name: String,
    val disable: String? = null,
    @SerializedName("top_pick") val topPick: Int
)

data class Period(
    val id: String?,
    val value: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)

data class Era(
    val id: String,
    val value: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

data class GmapLocation(
    val type: String,
    val coordinates: List<Double>
)

data class OpeningHours(
    val sunday: List<String>? = null,
    val monday: List<String>? = null,
    val tuesday: List<String>? = null,
    val wednesday: List<String>? = null,
    val thursday: List<String>? = null,
    val friday: List<String>? = null,
    val saturday: List<String>? = null
)
data class TranslatedOpeningHours(
    val sunday: DayTime? = null,
    val monday: DayTime? = null,
    val tuesday: DayTime? = null,
    val wednesday: DayTime? = null,
    val thursday: DayTime? = null,
    val friday: DayTime? = null,
    val saturday: DayTime? = null,
)

data class DayTime(
    val day: String,
    val time: String
)

data class TicketPrice(
    val type: String,
    val price: Int
)

data class Review(
    val id: String,
    val experience: String,
    val name: String,
    val rating: Int,
    val comment: String,
    @SerializedName("created_at") val createdAt: String
)
data class Pagination( val list: Any?)