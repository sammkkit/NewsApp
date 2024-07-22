package com.example.newsapp.Presentation.news_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.Domain.Model.Article
import com.example.newsapp.Presentation.Component.BottomSheetContent
import com.example.newsapp.Presentation.Component.CategoryTabRow
import com.example.newsapp.Presentation.Component.NewsArticleCard
import com.example.newsapp.Presentation.Component.NewsScreenTopBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    states: NewsScreeStates,
    onEvent: (NewsScreenEvents) -> Unit,
    onReadFull:(String)->Unit
) {
    val isRefreshing by remember { mutableStateOf(false) }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = states.isLoading)

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf(
        "General", "Business", "Health", "Science", "Sports", "Technology", "Entertainment"
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { categories.size }
    )
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    if(shouldBottomSheetShow){
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow=false },
            sheetState = sheetState,

        ) {
            states.selectedArticle?.let {
                BottomSheetContent(
                    article = states.selectedArticle!!,
                    onReadFullStoryClick = {
                        onReadFull(it.url)
                        coroutineScope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if(!sheetState.isVisible){
                                shouldBottomSheetShow=false
                            }
                        }
                    }
                )
            }
        }
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect {
            onEvent(NewsScreenEvents.onCategoryChange(categories[it]))
        }
    }


    Scaffold(

        topBar = {
            NewsScreenTopBar(
                scrollBehavior = scrollBehavior,
                onSearchClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categoryList = categories,
                onTabSelected = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
            HorizontalPager(
                state = pagerState
            ) {
//                NewsScreenList(states = states) {
//                    shouldBottomSheetShow=true
//                    onEvent(NewsScreenEvents.onNewsCardClicked(it))
//                }
                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = {

                        onEvent(NewsScreenEvents.onCategoryChange(categories[pagerState.currentPage]))
                    }
                ) {
                    NewsScreenList(states = states) {
                        shouldBottomSheetShow = true
                        onEvent(NewsScreenEvents.onNewsCardClicked(it))
                    }
                }

            }

        }


    }


}

@Composable
fun NewsScreenList(
    states: NewsScreeStates,
    onCardClick: (Article) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(states.articles) { article ->

            NewsArticleCard(
                article = article,
                onClick = {
                    onCardClick(article)
                }
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (states.isLoading) {
            CircularProgressIndicator()
        }

    }


}

//
//@Preview(showBackground = true)
//@Composable
//fun prev(){
//    NewsScreen()
//}