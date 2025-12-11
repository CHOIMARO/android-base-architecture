package com.company.domain.usecase

import com.company.domain.model.ImageItem
import com.company.domain.repository.ExampleRepository
import javax.inject.Inject

class ExampleUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {
    suspend operator fun invoke(query: String): List<ImageItem> {
        return exampleRepository.searchImages(query)
    }
}