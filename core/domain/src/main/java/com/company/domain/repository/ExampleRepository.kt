package com.company.domain.repository

import com.company.domain.model.ImageItem

interface ExampleRepository {
    // 이미지 검색 (Domain 모델 반환)
    suspend fun searchImages(query: String): List<ImageItem>
}