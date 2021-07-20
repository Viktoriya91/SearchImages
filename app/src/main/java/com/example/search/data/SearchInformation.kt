package com.example.search.data


import com.google.gson.annotations.SerializedName

data class SearchInformation(
    @SerializedName("image_results_state")
    val imageResultsState: String,
    @SerializedName("query_displayed")
    val queryDisplayed: String
)