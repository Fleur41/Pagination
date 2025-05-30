package com.sam.pagination.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PaginationViewmodel @Inject constructor(
    private  val repository: PaginationRepository
): ViewModel() {
    val repositories: Flow<PagingData<Repository>> =
        repository
            .searchRepositories()
            .cachedIn(viewModelScope)
}