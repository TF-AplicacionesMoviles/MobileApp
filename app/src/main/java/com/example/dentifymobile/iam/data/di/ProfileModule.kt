package com.example.dentifymobile.iam.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.remote.services.ProfileService
import com.example.dentifymobile.iam.data.repository.ProfileRepositoryImpl
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.iam.domain.repository.ProfileRepository
import com.example.dentifymobile.iam.domain.usecases.GetUserInfoUseCase
import com.example.dentifymobile.iam.domain.usecases.UpdateInformationUseCase
import com.example.dentifymobile.iam.domain.usecases.UpdatePasswordUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProfileModule {
    private fun getRetrofit(context: Context): Retrofit {
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

    fun provideProfileService(context: Context): ProfileService{
        return getRetrofit(context).create(ProfileService::class.java)
    }

    fun provideProfileRepository(context: Context): ProfileRepository{
        return ProfileRepositoryImpl(provideProfileService(context))
    }

    fun provideGetUserInfoUseCase(context: Context): GetUserInfoUseCase{
        return GetUserInfoUseCase(provideProfileRepository(context))
    }

    fun provideUpdateInformationUseCase(context: Context): UpdateInformationUseCase{
        return UpdateInformationUseCase(provideProfileRepository(context))
    }

    fun provideUpdatePasswordUseCase (context: Context): UpdatePasswordUseCase{
        return UpdatePasswordUseCase(provideProfileRepository(context))
    }



}