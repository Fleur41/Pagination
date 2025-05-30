package com.sam.pagination.pagination


import androidx.paging.PagingSource
import androidx.paging.PagingState
import javax.inject.Inject

const val FIRST_PAGE = 1

class RepositoryPagingSource @Inject constructor(
    private  val api: GitHubApi
): PagingSource<Int, Repository>() {
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        try {
            val page = params.key ?: FIRST_PAGE
            val pageSize = params.loadSize
            val response = api.searchRepositories(page = page, pageSize = pageSize )
            return  LoadResult.Page(
                data = response.items,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if(response.items.isNotEmpty()) page + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }


}