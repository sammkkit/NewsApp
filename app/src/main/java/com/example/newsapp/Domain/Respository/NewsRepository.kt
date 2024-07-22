package com.example.newsapp.Domain.Respository

import com.example.newsapp.Domain.Model.Article
import com.example.newsapp.util.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String,
    ):Resource<List<Article>>
}