package com.sam.pagination.pagination

import javax.inject.Inject

class PaginationRepository @Inject constructor(
    private val gitHubApi: GitHubApi
) {
    suspend fun searchRepositories(page: Int, pageSize: Int){
        gitHubApi.searchRepositories(page = page, pageSize = pageSize )
    }
}