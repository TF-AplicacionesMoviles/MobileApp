package com.example.dentifymobile.iam.data.di

import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.remote.services.AuthApiService
import com.example.dentifymobile.iam.data.repository.AuthRepositoryImpl
import com.example.dentifymobile.iam.domain.repository.AuthRepository
import com.example.dentifymobile.iam.domain.usecases.LoginUseCase
import com.example.dentifymobile.iam.domain.usecases.RegisterUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthModule {

    // Proveer instancia de AuthApiService
    fun provideAuthApiService(): AuthApiService {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }

    // Proveer instancia de AuthRepository
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl(provideAuthApiService())
    }

    // Proveer instancia de LoginUseCase
    fun provideLoginUseCase(): LoginUseCase {
        return LoginUseCase(provideAuthRepository())
    }

    fun provideRegisterUseCase(): RegisterUseCase {
        return RegisterUseCase(provideAuthRepository())
    }
}
