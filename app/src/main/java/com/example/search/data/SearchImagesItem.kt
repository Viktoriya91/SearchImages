package com.example.search.data


import com.google.gson.annotations.SerializedName

data class SearchImagesItem(
    @SerializedName("search_metadata")
    val searchMetadata: SearchMetadata,
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters,
    @SerializedName("search_information")
    val searchInformation: SearchInformation,
    @SerializedName("suggested_searches")
    val suggestedSearches: List<SuggestedSearche>,
    @SerializedName("images_results")
    val imagesResults: List<ImagesResult>
)