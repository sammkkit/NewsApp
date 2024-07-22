package com.example.newsapp.Presentation.Component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabRow(
    pagerState: PagerState,
    categoryList: List<String>,
    onTabSelected: (Int) -> Unit
){
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
    ) {
        categoryList.forEachIndexed { index, category ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onTabSelected(index) },
                content = {
                    Text(
                        text = category,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (pagerState.currentPage == index) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                        }
                    )
                }
            )
        }
        
    }
}