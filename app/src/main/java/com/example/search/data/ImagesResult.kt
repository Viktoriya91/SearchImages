package com.example.search.data

import java.io.Serializable

data class ImagesResult(
    val position: Int,
    val thumbnail: String,
    val source: String,
    val title: String,
    val link: String,
    val original: String
) : Serializable