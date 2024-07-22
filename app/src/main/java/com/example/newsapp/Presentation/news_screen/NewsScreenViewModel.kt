package com.example.newsapp.Presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Domain.Model.Article
import com.example.newsapp.Domain.Respository.NewsRepository
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    var newsList by mutableStateOf<List<Article>>(emptyList())
    var errorMessage by mutableStateOf<String?>(null)

    var state by mutableStateOf(NewsScreeStates())

    fun onEvent(event: NewsScreenEvents) {
        when (event) {
            is NewsScreenEvents.onCategoryChange -> {
                state = state.copy(category = event.category)
                getNewsArticles(event.category)
            }
            is NewsScreenEvents.onNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            NewsScreenEvents.onCloseIconClicked -> TODO()
            NewsScreenEvents.onSearchIconClicked -> TODO()
            is NewsScreenEvents.onSearchQueryChange -> TODO()
        }
    }

    init {
        getNewsArticles("general")
    }
    private fun getNewsArticles(category: String) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            val result = newsRepository.getTopHeadlines(category)
            when (result) {
                is Resource.Success -> {
                    state = state.copy(
                        articles = result.data ?: emptyList()
                        , isLoading = false
                    )

                }

                is Resource.Error -> {
                    errorMessage = result.message!!
                }
            }
        }
    }
    fun refreshNews() {
        getNewsArticles(state.category)
    }
}