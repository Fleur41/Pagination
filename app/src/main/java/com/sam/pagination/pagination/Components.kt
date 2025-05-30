package com.sam.pagination.pagination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems

inline fun <T: Any> LazyListScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((item: T) -> Any)? = null,
    noinline contentType: (item: T) -> Any? = { null },
    crossinline itemContent: @Composable LazyItemScope. (item: T) -> Unit

) = items(
    count = items.itemCount,
    key = if (key != null) { index: Int -> key(items[index]!!) } else null,
    contentType = { index: Int -> contentType(items[index]!!) }

) {
    itemContent(items [it]!!)

}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()

    }
}
@Composable
fun ErrorItemWithRetry(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.error,
    onClickRetryClick: () -> Unit
) {
    Box(
        modifier = modifier,
//        contentAlignment = Alignment.Center

    ) {
        Row (
            modifier = Modifier.align ( Alignment.Center ),
            verticalAlignment = Alignment.CenterVertically,

        ){
            Text(
                text = text,
                color = color,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { onClickRetryClick() }) {
                Text(text = "Retry")
            }
        }

    }
}
