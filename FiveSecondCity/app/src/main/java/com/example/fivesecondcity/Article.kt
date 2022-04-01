package com.example.fivesecondcity

// TODO: Flesh this out, check what can be accessed via the API
data class Article(
    val id: Int,
    val published_at: Double, // Not sure about the datatype here
    val preview: String,
    val title: String,
    val content: String,
    val short_content: String,
    val interests: List<String>,
    val followup: String,
    val link: String
)
