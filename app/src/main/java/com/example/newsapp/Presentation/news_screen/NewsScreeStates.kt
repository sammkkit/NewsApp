package com.example.newsapp.Presentation.news_screen

import com.example.newsapp.Domain.Model.Article

data class NewsScreeStates(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val error: String = "",
    val isSearchBarVisible:Boolean = false,
    val selectedArticle: Article? = null,
    val category: String = "general",
    val searchQuery: String = "",
)
