package com.example.newsapp.Presentation.news_screen

import com.example.newsapp.Domain.Model.Article

sealed class NewsScreenEvents {
    data class onNewsCardClicked(
        val article: Article
    ):NewsScreenEvents()

    data class onCategoryChange(
        val category: String
    ):NewsScreenEvents()

    data class onSearchQueryChange(
        val searchQuery: String
    ):NewsScreenEvents()

    object onSearchIconClicked :NewsScreenEvents()
    object onCloseIconClicked :NewsScreenEvents()
}