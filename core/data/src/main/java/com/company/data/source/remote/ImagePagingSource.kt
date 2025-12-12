package com.company.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.company.data.api.PixabayApi
import com.company.data.mapper.toDomainModel
import com.company.domain.model.ImageItem
import java.io.IOException

class ImagePagingSource(
    private val pixabayApi: PixabayApi,
    private val query: String,
    private val perPage: Int
) : PagingSource<Int, ImageItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageItem> {
        val page = params.key ?: 1
        
        return try {
            val response = pixabayApi.searchImagesPagingData(
                query = query,
                page = page,
                perPage = perPage
            )
            val images = response.hits.map { it.toDomainModel() }

            LoadResult.Page(
                data = images,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (images.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImageItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}