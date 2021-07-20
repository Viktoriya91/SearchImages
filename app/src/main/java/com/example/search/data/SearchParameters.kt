package com.example.search.data


import com.google.gson.annotations.SerializedName

data class SearchParameters(
    val engine: String,
    val q: String,
    @SerializedName("google_domain")
    val googleDomain: String,
    val ijn: String,
    val device: String,
    val tbm: String
)