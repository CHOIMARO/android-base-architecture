package com.company.data.service

import com.company.data.model.PixabayImageResponse
import com.company.data.model.PixabayVideoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {

    /**
     * 이미지 API 호출 (BASE_URL/api/)
     * @param query 검색어
     * @param image_type 이미지 유형 (예: "photo")
     */
    @GET(".") // BASE_URL 자체가 이미지 API 엔드포인트
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo",
    ): PixabayImageResponse

    /**
     * 이미지 API 호출 (BASE_URL/api/)
     * @param query 검색어
     * @param image_type 이미지 유형 (예: "photo")
     */
    @GET(".") // BASE_URL 자체가 이미지 API 엔드포인트
    suspend fun searchImagesPagingData(
        @Query("q") query: String,
        @Query("image_type") imageType: String = "photo",
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PixabayVideoResponse

    /**
     * 특정 이미지 ID로 이미지 정보 가져오기
     */
    @GET(".")
    suspend fun getImageById(
        @Query("id") id: String
    ): PixabayImageResponse //

    /**
     * 비디오 API 호출 (BASE_URL/api/videos/)
     * @param query 검색어
     */
    @GET("videos/")
    suspend fun searchVideos(
        @Query("q") query: String
    ): PixabayVideoResponse

    /**
     * 특정 비디오 ID로 비디오 정보 가져오기
     */
    @GET("videos/")
    suspend fun getVideoById(
        @Query("id") id: String
    ): PixabayVideoResponse
}