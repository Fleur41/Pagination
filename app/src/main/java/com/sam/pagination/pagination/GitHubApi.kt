package com.sam.pagination.pagination

import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories?q=language:kotlin&sort=stars")
    suspend fun searchRepositories(
        @Query("q") query: String = "language:kotlin",
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): SearchRepositoryResponse
}