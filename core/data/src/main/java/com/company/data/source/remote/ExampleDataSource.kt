package com.company.data.source.remote

import com.company.data.mapper.toDomainModel
import com.company.data.service.PixabayApiService
import com.company.domain.model.ImageItem
import javax.inject.Inject

class ExampleDataSource @Inject constructor(
    private val pixabayApiService: PixabayApiService
) {
    suspend fun searchImages(query: String): List<ImageItem> {
        return pixabayApiService.searchImages(query).hits.map { it.toDomainModel() }
    }
}