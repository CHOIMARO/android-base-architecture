package com.company.domain.repository

import androidx.paging.PagingData
import com.company.domain.model.ImageItem
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    // 이미지 검색 (Domain 모델 반환)
    suspend fun searchImages(query: String): List<ImageItem>

    // Paging을 이용한 이미지 검색 (Domain 모델 반환)
    suspend fun searchImagesPagingData(query: String, perPage: Int): Flow<PagingData<ImageItem>>
}