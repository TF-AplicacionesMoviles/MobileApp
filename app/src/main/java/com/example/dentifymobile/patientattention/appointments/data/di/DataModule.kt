package com.example.dentifymobile.patientattention.appointments.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.patientattention.appointments.data.remote.services.AppointmentService
import com.example.dentifymobile.patientattention.appointments.data.repository.AppointmentRepositoryImpl
import com.example.dentifymobile.patientattention.appointments.domain.repository.AppointmentRepository
import com.example.dentifymobile.patientattention.appointments.domain.usecases.AddAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.DeleteAppointmentUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAllAppointmentsUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAppointmentByIdUseCase
import com.example.dentifymobile.patientattention.appointments.domain.usecases.UpdateAppointmentUseCase
import com.example.dentifymobile.patientattention.patient.data.di.DataModule.getPatientRepository
import com.example.dentifymobile.patientattention.patient.domain.usecases.GetAllPatientFormInfoUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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

    fun getAppointmentService(context: Context): AppointmentService{
        return getRetrofit(context).create(AppointmentService::class.java)
    }

    fun getAppointmentRepository(context: Context): AppointmentRepository{
        return AppointmentRepositoryImpl(getAppointmentService(context))
    }
    fun getAllAppointmentsUseCase(context: Context): GetAllAppointmentsUseCase {
        return GetAllAppointmentsUseCase(getAppointmentRepository(context))
    }
    fun deleteAppointmentUseCase(context: Context): DeleteAppointmentUseCase {
        return DeleteAppointmentUseCase(getAppointmentRepository(context))
    }
    fun addAppointmentUseCase(context: Context): AddAppointmentUseCase {
        return AddAppointmentUseCase(getAppointmentRepository(context))
    }
    fun updateAppointmentUseCase(context: Context): UpdateAppointmentUseCase{
        return UpdateAppointmentUseCase(getAppointmentRepository(context))
    }
    fun getAllPatientFormInfoUseCase(context: Context): GetAllPatientFormInfoUseCase {
        return GetAllPatientFormInfoUseCase(getPatientRepository(context))
    }
    fun getAppointmentByIdUseCase(context: Context): GetAppointmentByIdUseCase{
        return GetAppointmentByIdUseCase(getAppointmentRepository(context))
    }
}