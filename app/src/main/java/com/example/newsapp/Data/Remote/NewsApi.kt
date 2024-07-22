package com.example.newsapp.Data.Remote

import com.example.newsapp.Domain.Model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
//    GET https://newsapi.org/v2/top-headlines?country=us&apiKey=6b9fb3878f2c4c15a3e1210f65ccb1e4


    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category")
        category: String,
        @Query("country")
        countryCode: String = "in",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse
    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "6b9fb3878f2c4c15a3e1210f65ccb1e4"
    }
}