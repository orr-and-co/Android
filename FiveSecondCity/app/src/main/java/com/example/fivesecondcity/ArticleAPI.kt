package com.example.fivesecondcity

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://cardiff.jellypro.xyz/api/v1/"

interface ArticleAPI {

    @GET("posts/recent")
    fun getArticles(): Call<List<Article>>

    @GET("posts/{id}")
    fun getArticle(@Path(value = "id", encoded = true) id: Int): Call<Article>

    companion object {
        operator fun invoke(): ArticleAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleAPI::class.java)
        }
    }
}

data class IDRequest(
    val id: Int
)