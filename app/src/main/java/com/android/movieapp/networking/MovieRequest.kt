package com.android.movieapp.networking

import com.android.movieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequest {

    private var retrofit: Retrofit? = null

//  Agar tidak selalu membuat link baru
    private const val BASE_URL = "http://omdbapi.com/"

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {

                val builder = OkHttpClient.Builder()
                builder.retryOnConnectionFailure(false)

                // Hanya dilihat pada saat Debug saja
                builder.interceptors().add(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                })

                val client = builder.build()

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}