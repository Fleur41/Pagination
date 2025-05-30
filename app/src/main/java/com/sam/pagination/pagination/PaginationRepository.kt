package com.sam.pagination.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaginationRepository @Inject constructor(
    private val api: GitHubApi
) {
    fun searchRepositories( pageSize: Int = 20): Flow<PagingData<Repository>> {

        return Pager(config = PagingConfig(
            pageSize = pageSize,
            initialLoadSize = 40,
            prefetchDistance = 10
            )
        ){
            RepositoryPagingSource(api)
        }.flow
    }
}