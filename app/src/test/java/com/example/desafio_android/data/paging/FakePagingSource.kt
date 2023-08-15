package com.example.desafio_android.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.desafio_android.domain.GHJavaRepositoryDO
import com.example.desafio_android.domain.GHJavaRepositoryUserDO
import kotlin.math.max


private const val STARTING_KEY = 0
private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

class FakePagingSource(
) : PagingSource<Int, GHJavaRepositoryDO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GHJavaRepositoryDO> {
        val start = params.key ?: STARTING_KEY
        val range = start.until(start + params.loadSize)

        return LoadResult.Page(
            data = range.map {
                GHJavaRepositoryDO(
                    it.toLong(),
                    it.toString(),
                    it.toString(),
                    GHJavaRepositoryUserDO(
                        it.toLong(),
                        it.toString(),
                        it.toString(),
                        it.toString()
                    ),
                    it.toString(),
                    it.toLong(),
                    it.toLong()
                )
            },
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = range.first - params.loadSize)
            },
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, GHJavaRepositoryDO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = (article.id - (state.config.pageSize)).toInt())
    }
}