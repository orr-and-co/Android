package com.example.fivesecondcity

// TODO: Flesh this out, check what can be accessed via the API
data class Article(
    val id: Int,
    val published_at: Double, // Not sure about the datatype here
    val image: String,
    val title: String,
    val content: String
)
