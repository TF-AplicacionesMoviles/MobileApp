package com.example.dentifymobile.patientattention.patient.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.patientattention.patient.data.remote.services.MedicalHistoryService
import com.example.dentifymobile.patientattention.patient.data.remote.services.PatientService
import com.example.dentifymobile.patientattention.patient.data.repository.MedicalHistoryRepositoryImpl
import com.example.dentifymobile.patientattention.patient.data.repository.PatientRepositoryImpl
import com.example.dentifymobile.patientattention.patient.domain.usecases.AddMedicalHistoryUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.AddPatientUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.DeletePatientUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.GetAllMedicalHistoriesUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.GetAllPatientsUseCase
import com.example.dentifymobile.patientattention.patient.domain.usecases.UpdatePatientUseCase
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

    fun getPatientService(context: Context): PatientService {
        return getRetrofit(context).create(PatientService::class.java)
    }

    fun getPatientRepository(context: Context): PatientRepositoryImpl {
        return PatientRepositoryImpl(getPatientService(context))
    }

    fun getAllPatientsUseCase(context: Context): GetAllPatientsUseCase {
        return GetAllPatientsUseCase(getPatientRepository(context))
    }

    fun addPatientUseCase(context: Context): AddPatientUseCase {
        return AddPatientUseCase(getPatientRepository(context))
    }

    fun deletePatientUseCase(context: Context): DeletePatientUseCase {
        return DeletePatientUseCase(getPatientRepository(context))
    }

    fun updatePatientUseCase(context: Context): UpdatePatientUseCase {
        return UpdatePatientUseCase(getPatientRepository(context))
    }

    fun getMedicalHistoryService(context: Context): MedicalHistoryService {
        return getRetrofit(context).create(MedicalHistoryService::class.java)
    }

    fun getMedicalHistoryRepository(context: Context): MedicalHistoryRepositoryImpl {
        return MedicalHistoryRepositoryImpl(getMedicalHistoryService(context))
    }

    fun getAllMedicalHistoriesUseCase(context: Context): GetAllMedicalHistoriesUseCase {
        return GetAllMedicalHistoriesUseCase(getMedicalHistoryRepository(context))
    }

    fun addMedicalHistoryUseCase(context: Context): AddMedicalHistoryUseCase {
        return AddMedicalHistoryUseCase(getMedicalHistoryRepository(context))
    }
}