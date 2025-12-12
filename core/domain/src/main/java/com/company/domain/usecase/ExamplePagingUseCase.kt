package com.company.domain.usecase

import androidx.paging.PagingData
import com.company.domain.model.ImageItem
import com.company.domain.repository.ExampleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExamplePagingUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(query: String, perPage: Int): Flow<PagingData<ImageItem>> {
        return exampleRepository.searchImagesPagingData(query, perPage)
    }
}