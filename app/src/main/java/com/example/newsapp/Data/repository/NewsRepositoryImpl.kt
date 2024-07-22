@file:Suppress("UNREACHABLE_CODE")

package com.example.newsapp.Data.repository

import com.example.newsapp.Data.Remote.NewsApi
import com.example.newsapp.Domain.Model.Article
import com.example.newsapp.Domain.Respository.NewsRepository
import com.example.newsapp.util.Resource

class NewsRepositoryImpl(
    private val newsApi: NewsApi
) :NewsRepository{
    override suspend fun getTopHeadlines(category: String): Resource<List<Article>> {
        return try{
            val response = newsApi.getBreakingNews(category).articles
            Resource.Success(response)
        }catch (e:Exception){
            Resource.Error(e.message.toString())
        }
    }

}