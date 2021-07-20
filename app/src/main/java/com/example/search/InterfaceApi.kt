package com.example.search

import com.example.search.data.SearchImagesItem
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface InterfaceApi {
    @GET("/search.json")
    suspend fun searchImages(
        @Query("q") q : String,
        @Query("tbm") tbm : String,
        @Query("ijn") ijn : Int
    ): SearchImagesItem

    companion object{
        operator fun invoke(): InterfaceApi{
            val requestInterceptor = Interceptor{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key",ConstantsForCode.CLIENT_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor (requestInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ConstantsForCode.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InterfaceApi::class.java)
        }
    }
}