package com.company.data.source.remote

import com.company.data.mapper.toDomainModel
import com.company.data.api.PixabayApi
import com.company.domain.model.ImageItem
import javax.inject.Inject

class ExampleDataSource @Inject constructor(
    private val pixabayApi: PixabayApi
) {
    suspend fun searchImages(query: String): List<ImageItem> {
        return pixabayApi.searchImages(query).hits.map { it.toDomainModel() }
    }
}