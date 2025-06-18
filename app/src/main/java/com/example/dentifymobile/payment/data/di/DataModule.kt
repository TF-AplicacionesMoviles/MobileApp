package com.example.dentifymobile.payment.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.patientattention.appointments.data.di.DataModule.getAppointmentRepository
import com.example.dentifymobile.patientattention.appointments.domain.usecases.GetAllAppointmentsFormInfoUseCase
import com.example.dentifymobile.payment.data.remote.services.InvoiceService
import com.example.dentifymobile.payment.data.repository.InvoiceRepositoryImpl
import com.example.dentifymobile.payment.domain.repository.InvoiceRepository
import com.example.dentifymobile.payment.domain.usecases.AddInvoiceUseCase
import com.example.dentifymobile.payment.domain.usecases.GetAllInvoicesUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataModule {
    fun getInvoiceService(context: Context): InvoiceService{
        return getRetrofit(context).create(InvoiceService::class.java)
    }

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

    fun getInvoiceRepository(context: Context): InvoiceRepository{
        return InvoiceRepositoryImpl(getInvoiceService(context))
    }

    fun getAllInvoicesUseCase(context: Context): GetAllInvoicesUseCase{
        return GetAllInvoicesUseCase(getInvoiceRepository(context))
    }

    fun addInvoiceUseCase(context: Context): AddInvoiceUseCase{
        return AddInvoiceUseCase(getInvoiceRepository(context))
    }
    fun getAllAppointmentsFormInfoUseCase(context: Context): GetAllAppointmentsFormInfoUseCase {
        return GetAllAppointmentsFormInfoUseCase(getAppointmentRepository(context))
    }
}