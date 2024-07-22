package com.example.newsapp.Presentation.Component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "News", fontWeight = FontWeight.Bold)
        },
        actions = {
//            IconButton(onClick = onSearchClick) {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search"
//                )
//            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
            , titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

    )
}