package com.example.desafio_android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.desafio_android.data.dataclasses.dto.GHJavaRepositoryDTO
import com.example.desafio_android.data.repository.AppDataSource

class ExamplePagingSource(
    private val appDataSource: AppDataSource,
    //private val query: String
) : PagingSource<Int, GHJavaRepositoryDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GHJavaRepositoryDTO> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = appDataSource.getJavaRepositories(nextPageNumber)
            val range = nextPageNumber.until(nextPageNumber + params.loadSize)
            //val response = appDataSource.getJavaRepositories(query, nextPageNumber)
            return LoadResult.Page(
                data = response.dataObtained,
                prevKey = null, // Only paging forward.
                nextKey = range.last + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GHJavaRepositoryDTO>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}