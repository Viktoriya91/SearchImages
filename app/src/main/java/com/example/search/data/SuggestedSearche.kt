package com.example.search.data


import com.google.gson.annotations.SerializedName

data class SuggestedSearche(
    val name: String,
    val link: String,
    val chips: String,
    @SerializedName("serpapi_link")
    val serpapiLink: String,
    val thumbnail: String
)