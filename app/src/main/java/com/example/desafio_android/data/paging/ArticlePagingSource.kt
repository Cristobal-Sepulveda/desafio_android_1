package com.example.desafio_android.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.desafio_android.BuildConfig
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApi
import com.example.desafio_android.data.apiservices.GitHubJavaRepositoriesApiService
import com.example.desafio_android.data.apiservices.GitHubUsersApi
import com.example.desafio_android.data.dataclasses.domainObjects.GHJavaRepositoryDO
import com.example.desafio_android.data.dataclasses.dto.asDomainModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import kotlin.math.max

// The initial key used for loading.
// This is the article id of the first article that will be loaded
private const val STARTING_KEY = 0
private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

class ArticlePagingSource(

) : PagingSource<Int, GHJavaRepositoryDO>() {

    /*The load() function will be called by the Paging library to asynchronously fetch
     more data to be displayed as the user scrolls around. The LoadParams object keeps
     information related to the load operation, including the following:

      1) Key of the page to be loaded - If this is the first time that load() is called,
      LoadParams.key will be null. In this case, you will have to define the initial page key.
      For our project, we use the article ID as the key. Let's also add a STARTING_KEY constant
      of 0 to the top of the ArticlePagingSource file for the initial page key.

      2) Load size - the requested number of items to load.**/


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GHJavaRepositoryDO> {
        try {
            // Start paging with the STARTING_KEY if this is the first load
            val start = params.key ?: STARTING_KEY
            Log.e("load", "$start - ${params.loadSize}")
            Log.e("ArticlePagingSource - Load", "Page request: ${((start / params.loadSize) + 1)}")
            // Load as many items as hinted by params.loadSize
            val range = start.until(start + params.loadSize)
            // Make the API call to fetch the data for the specified page
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
                        Log.e("ArticlePagingSource - Load", "Setting Name Error: ${secondApiResponse.message()}")
                    }
                }
                // Return the data for the current page
                return LoadResult.Page(
                    data = repositories.map { it.asDomainModel(it) },
                    prevKey = when (start) {
                        STARTING_KEY -> null
                        else -> ensureValidKey(key = range.first - params.loadSize)
                    },
                    nextKey = if (repositories.isNotEmpty()) range.last + 1 else null
                )
            } else {
                // Handle API error
                Log.e("ArticlePagingSource - Load", "Handle API error: ${apiResponse.message()}")
                return LoadResult.Error(Exception(apiResponse.message()))
            }
        } catch (e: Exception) {
            Log.e("ArticlePagingSource - Load", "Catch error: ${e.message.toString()}")
            // Handle other errors
            return LoadResult.Error(e)
        }
    }

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, GHJavaRepositoryDO>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer

        //The last index that successfully fetched data when read is the anchorPosition.
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null

        Log.e("getRefreshKey", "$anchorPosition")
        //When we're refreshing, we grab the key of the Article closest to the anchorPosition
        // to use as the load key. That way, when we start loading again from a new PagingSource,
        // the set of fetched items includes items that were already loaded, which ensures a
        // smooth and consistent user experience.

        return ensureValidKey(key = (article.id - (state.config.pageSize)).toInt())
    }
}