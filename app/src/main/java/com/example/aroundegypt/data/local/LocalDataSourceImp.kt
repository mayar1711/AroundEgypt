package com.example.aroundegypt.data.local

import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: ExperienceDao) : LocalDataSource {
}