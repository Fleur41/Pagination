package com.sam.pagination.pagination

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaginationViewmodel @Inject constructor(
    private  val repository: PaginationRepository
): ViewModel() {

}