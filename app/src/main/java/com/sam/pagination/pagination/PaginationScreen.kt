package com.sam.pagination.pagination

import android.R.attr.text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginationScreen(
    viewModel: PaginationViewmodel = hiltViewModel()
) {
    val repositories: LazyPagingItems<Repository> = viewModel.repositories.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "PaginationScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RepositoryList(
                modifier = Modifier.fillMaxSize(),
                repositories = repositories
            )
//            Text(text = "PaginationScreen")
//            Text(text = "count: ${repositories.itemCount}")
        }
    }
}

@Composable
fun RepositoryList(
    modifier: Modifier = Modifier,
    repositories: LazyPagingItems<Repository>
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){

        items(repositories){ repository ->
            RepositoryItem(
                modifier = Modifier.fillMaxWidth(),
                repository = repository
            )
        }
        repositories.apply {
            if(loadState.refresh is LoadState.Loading){
                item{
                    LoadingIndicator(modifier = Modifier.fillParentMaxSize())
                }
            }

            if(loadState.append is LoadState.Loading){
                item{
                    LoadingIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            if(loadState.append is LoadState.Error){
                item{
                    ErrorItemWithRetry(
                        text = "Something went wrong",
                        modifier = Modifier.fillMaxWidth(),
                        onClickRetryClick = { retry() }
                    )

                    LoadingIndicator()
                }
            }
        }
    }
}


@Composable
fun RepositoryItem(
    modifier: Modifier = Modifier,
    repository: Repository
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = repository.id.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {


                    Text(
                        text = repository.name,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    repository.description?.let{
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                }
            }


        }
    }
}