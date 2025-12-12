package com.company.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.company.data.api.PixabayApi
import com.company.data.source.remote.ExampleDataSource
import com.company.data.source.remote.ImagePagingSource
import com.company.domain.model.ImageItem
import com.company.domain.repository.ExampleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExampleRepositoryImpl @Inject constructor(
    private val exampleDataSource: ExampleDataSource,
    private val pixabayApi: PixabayApi,
) : ExampleRepository {
    override suspend fun searchImages(query: String): List<ImageItem> {
        return exampleDataSource.searchImages(query)
    }

    override suspend fun searchImagesPagingData(
        query: String,
        perPage: Int
    ): Flow<PagingData<ImageItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImagePagingSource(pixabayApi, query, perPage)
            }
        ).flow
    }
}