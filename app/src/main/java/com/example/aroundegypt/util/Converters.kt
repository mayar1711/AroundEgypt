package com.example.aroundegypt.util

import androidx.room.TypeConverter
import com.example.aroundegypt.data.model.City
import com.example.aroundegypt.data.model.Era
import com.example.aroundegypt.data.model.GmapLocation
import com.example.aroundegypt.data.model.OpeningHours
import com.example.aroundegypt.data.model.Period
import com.example.aroundegypt.data.model.Review
import com.example.aroundegypt.data.model.Tag
import com.example.aroundegypt.data.model.TicketPrice
import com.example.aroundegypt.data.model.TranslatedOpeningHours
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    private val gson = Gson()

    @TypeConverter
    fun listToJsonString(list : List<String>?): String? = list?.let { gson.toJson(it)}

    @TypeConverter
    fun fromJsonStringToList(string:String?):List<String>? = string?.let { gson.fromJson(it, object: TypeToken<List<String>>() {}.type)}

    @TypeConverter
    fun tagListToJsonString(list : List<Tag>?): String? = list?.let { gson.toJson(it)}

    @TypeConverter
    fun fromJsonStringToTagList(string:String?):List<Tag>? = string?.let { gson.fromJson(it, object: TypeToken<List<Tag>>() {}.type)}

    @TypeConverter
    fun fromJsonStringToCity(string: String?): City? = string?.let { gson.fromJson(it,City::class.java)}

    @TypeConverter
    fun cityToJsonString(city: City?): String? = city?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToPeriod(string: String?): Period? = string?.let { gson.fromJson(it,Period::class.java) }

    @TypeConverter
    fun periodToJsonString(period: Period?): String? = period?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToEra(string: String?): Era? = string?.let { gson.fromJson(it,Era::class.java) }

    @TypeConverter
    fun eraToJsonString(era: Era?): String? = era?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToGmapLocation(string: String?): GmapLocation? = string?.let { gson.fromJson(it,GmapLocation::class.java) }

    @TypeConverter
    fun gmapLocationToJsonString(gmapLocation: GmapLocation?): String? = gmapLocation?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToOpeningHours(string: String?): OpeningHours? = string?.let { gson.fromJson(it,OpeningHours::class.java) }

    @TypeConverter
    fun openingHoursToJsonString(openingHours: OpeningHours?): String? = openingHours?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToTranslatedOpeningHours(string: String?): TranslatedOpeningHours? = string?.let { gson.fromJson(it,TranslatedOpeningHours::class.java) }

    @TypeConverter
    fun translatedOpeningHoursToJsonString(translatedOpeningHours: TranslatedOpeningHours?): String? = translatedOpeningHours?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToTicketPriceList(string: String?): List<TicketPrice>? = string?.let { gson.fromJson(it, object : TypeToken<List<TicketPrice>>(){}.type) }

    @TypeConverter
    fun ticketPriceListToJsonString(list: List<TicketPrice>?): String? = list?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonStringToReviewList(string: String?): List<Review>? = string?.let { gson.fromJson(it, object : TypeToken<List<Review>>(){}.type) }

    @TypeConverter
    fun reviewListToJsonString(list: List<Review>?): String? = list?.let { gson.toJson(it) }

    @TypeConverter
    fun fromJsonToDoubleList(string:String?): List<Double>? = string?.let { gson.fromJson(it,object : TypeToken<List<Double>>(){}.type)}
    @TypeConverter
    fun doubleListToJsonString(list:List<Double>?):String? = list?.let { gson.toJson(it)}
}