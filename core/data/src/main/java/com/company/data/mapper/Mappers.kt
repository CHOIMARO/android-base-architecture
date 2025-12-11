package com.company.data.mapper

import com.company.data.model.PixabayImageHitResponse
import com.company.data.model.PixabayVideoHitResponse
import com.company.domain.model.ImageItem
import com.company.domain.model.VideoItem

fun PixabayImageHitResponse.toDomainModel(): ImageItem {
    return ImageItem(
        id = this.id.toString(),
        imageUrl = this.imageUrl,
        type = this.type,
        user = this.user,
        tags = this.tags.split(",").map { it.trim() },
        views = this.views,
        likes = this.likes,
        downloads = this.downloads,
    )
}

fun PixabayVideoHitResponse.toDomainModel(): VideoItem {
    val thumbnailUrl = this.videos.medium.thumbnail

    return VideoItem(
        id = this.id.toString(),
        videoUrl = this.videos.medium.url,
        thumbnailUrl = thumbnailUrl,
        type = this.type,
        user = this.user,
        tags = this.tags.split(",").map { it.trim() },
        views = this.views,
        likes = this.likes,
        downloads = this.downloads,
    )
}