package com.example.desafio_android.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import com.example.desafio_android.data.dataclasses.returns.ApiPullRequestResponse
import com.example.desafio_android.data.source.AppDataSource
import com.example.desafio_android.data.source.network.NetworkDataSource
import kotlin.math.max

private const val STARTING_KEY = 0
private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

class GhJRsPagingSource(
    private val networkDataSource: AppDataSource
) : PagingSource<Int, GHJavaRepositoryDO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GHJavaRepositoryDO> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)
        val page = (start/params.loadSize)+1

        val repositories = networkDataSource.getJavaRepositories(page)

        return if(repositories.isEmpty()){
            LoadResult.Error(Exception("Error"))
        }else{
            LoadResult.Page(
                data = repositories.map { it.asDomainModel(it) },
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(range.first - params.loadSize)
                },
                nextKey = if (repositories.isNotEmpty()) range.last + 1 else null
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GHJavaRepositoryDO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = (article.id - (state.config.pageSize)).toInt())
    }

}