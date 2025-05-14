package com.example.dentifymobile.iam.data.di

import com.example.dentifymobile.iam.data.remote.services.AuthApiService
import com.example.dentifymobile.iam.data.repository.AuthRepositoryImpl
import com.example.dentifymobile.iam.domain.repository.AuthRepository
import com.example.dentifymobile.iam.domain.usecases.LoginUseCase
import com.example.dentifymobile.iam.domain.usecases.RegisterUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AuthModule {

    private const val BASE_URL = "http://10.0.2.2:8081/api/"

    // Proveer instancia de AuthApiService
    fun provideAuthApiService(): AuthApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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
