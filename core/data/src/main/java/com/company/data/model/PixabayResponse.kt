package com.company.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

// --- 이미지 Response ---

@JsonClass(generateAdapter = true)
data class PixabayImageResponse(
    @field:Json(name = "hits") val hits: List<PixabayImageHitResponse>
)

@JsonClass(generateAdapter = true)
data class PixabayImageHitResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "largeImageURL") val imageUrl: String,
    @field:Json(name = "user") val user: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "tags") val tags: String,
    @field:Json(name = "views") val views: Int,
    @field:Json(name = "likes") val likes: Int,
    @field:Json(name = "downloads") val downloads: Int
)

// --- 비디오 Response ---

@JsonClass(generateAdapter = true)
data class PixabayVideoResponse(
    @field:Json(name = "hits") val hits: List<PixabayVideoHitResponse>
)

@JsonClass(generateAdapter = true)
data class PixabayVideoHitResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "user") val user: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "tags") val tags: String,
    @field:Json(name = "views") val views: Int,
    @field:Json(name = "likes") val likes: Int,
    @field:Json(name = "downloads") val downloads: Int,
    @field:Json(name = "videos") val videos: PixabayVideoUrlResponse
)

@JsonClass(generateAdapter = true)
data class PixabayVideoUrlResponse(
    @field:Json(name = "medium") val medium: PixabayVideoDetailResponse
)

@JsonClass(generateAdapter = true)
data class PixabayVideoDetailResponse(
    @field:Json(name = "url") val url: String,
    @field:Json(name = "thumbnail") val thumbnail: String
)