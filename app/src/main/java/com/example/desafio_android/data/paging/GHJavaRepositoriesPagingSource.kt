package com.example.desafio_android.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import kotlin.math.max

private const val STARTING_KEY = 0
private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

class GHJavaRepositoriesPagingSource(
) : PagingSource<Int, GHJavaRepositoryDO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GHJavaRepositoryDO> {
        try {
            val start = params.key ?: STARTING_KEY
            val range = start.until(start + params.loadSize)
            val apiResponse = GitHubJavaRepositoriesApi.RETROFIT_GITHUB
                .getJavaRepositoriesFromGithubApi(((start / params.loadSize) + 1).toString())
            if (apiResponse.isSuccessful) {
                val repositories = apiResponse.body()?.items ?: emptyList()
                for (repository in repositories) {
                    val loginName = repository.owner.login
                    val secondApiResponse = GitHubUsersApi
                        .cargarUrl("${BuildConfig.GITHUB_API_BASE_URL}users/")
                        .getUserData(loginName, BuildConfig.GITHUB_API_TOKEN)
                    if (secondApiResponse.isSuccessful) {
                        repository.owner.name = secondApiResponse.body()?.name ?: "No data"
                    }else{
                        repository.owner.name = "No Data"
                        Log.e("ArticlePagingSource - Load", "Setting Name Error: ${secondApiResponse.message()}")
                    }
                }

                return LoadResult.Page(
                    data = repositories.map { it.asDomainModel(it) },
                    prevKey = when (start) {
                        STARTING_KEY -> null
                        else -> ensureValidKey(key = range.first - params.loadSize)
                    },
                    nextKey = if (repositories.isNotEmpty()) range.last + 1 else null
                )
            } else {
                Log.e("ArticlePagingSource - Load", "Handle API error: ${apiResponse.message()}")
                return LoadResult.Error(Exception(apiResponse.message()))
            }
        } catch (e: Exception) {
            Log.e("ArticlePagingSource - Load", "Catch error: ${e.message.toString()}")
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GHJavaRepositoryDO>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = (article.id - (state.config.pageSize)).toInt())
    }
}