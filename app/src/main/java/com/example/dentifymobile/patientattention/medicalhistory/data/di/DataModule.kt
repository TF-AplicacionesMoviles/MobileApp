package com.example.dentifymobile.patientattention.medicalhistory.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.patientattention.medicalhistory.data.remote.services.MedicalHistoryService
import com.example.dentifymobile.patientattention.medicalhistory.data.repository.MedicalHistoryRepositoryImpl
import com.example.dentifymobile.patientattention.medicalhistory.domain.repository.MedicalHistoryRepository
import com.example.dentifymobile.patientattention.medicalhistory.domain.usecases.AddMedicalHistoryUseCase
import com.example.dentifymobile.patientattention.medicalhistory.domain.usecases.GetAllMedicalHistoriesUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
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

    fun getMedicalHistoryService(context: Context): MedicalHistoryService {
        return getRetrofit(context).create(MedicalHistoryService::class.java)
    }

    fun getMedicalHistoryRepository(context: Context): MedicalHistoryRepository {
        return MedicalHistoryRepositoryImpl(getMedicalHistoryService(context))
    }

    fun getAllMedicalHistoriesUseCase(context: Context): GetAllMedicalHistoriesUseCase {
        return GetAllMedicalHistoriesUseCase(getMedicalHistoryRepository(context))
    }

    fun addMedicalHistory(context: Context): AddMedicalHistoryUseCase {
        return AddMedicalHistoryUseCase(getMedicalHistoryRepository(context))
    }
}