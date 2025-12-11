package com.company.data.repository

import com.company.data.source.remote.ExampleDataSource
import com.company.domain.model.ImageItem
import com.company.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) : ExampleRepository {
    override suspend fun searchImages(query: String): List<ImageItem> {
        return exampleDataSource.searchImages(query)
    }
}