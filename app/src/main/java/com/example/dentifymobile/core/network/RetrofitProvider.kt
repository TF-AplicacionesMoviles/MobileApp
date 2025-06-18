package com.example.dentifymobile.core.network

import android.content.Context
import com.example.dentifymobile.iam.data.storage.TokenStorage
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    fun getRetrofit(context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = TokenStorage.getAccessToken(context)
                val request = chain.request().newBuilder().apply {
                    if (!token.isNullOrEmpty()) {
                        addHeader("Authorization", "Bearer $token")
                    }
                }.build()
                chain.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}