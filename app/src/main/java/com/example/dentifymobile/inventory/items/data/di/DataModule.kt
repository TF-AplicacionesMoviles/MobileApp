package com.example.dentifymobile.inventory.items.data.di

import android.content.Context
import com.example.dentifymobile.core.network.ApiConstants
import com.example.dentifymobile.iam.data.storage.TokenStorage
import com.example.dentifymobile.inventory.items.data.remote.services.ItemService
import com.example.dentifymobile.inventory.items.data.repository.ItemRepositoryImpl
import com.example.dentifymobile.inventory.items.domain.repository.ItemRepository
import com.example.dentifymobile.inventory.items.domain.usecases.CreateItemUseCase
import com.example.dentifymobile.inventory.items.domain.usecases.DeleteItemUseCase
import com.example.dentifymobile.inventory.items.domain.usecases.GetAllItemsUseCase
import com.example.dentifymobile.inventory.items.domain.usecases.UpdateItemUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    fun getItemService(context: Context): ItemService {
        return getRetrofit(context).create(ItemService::class.java)
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

    fun getItemRepository(context: Context): ItemRepository {
        return ItemRepositoryImpl(getItemService(context))
    }

    fun getAllItemsUseCase(context: Context): GetAllItemsUseCase {
        return GetAllItemsUseCase(getItemRepository(context))
    }

    fun createItemUseCase(context: Context): CreateItemUseCase {
        return CreateItemUseCase(getItemRepository(context))
    }

    fun updateItemUseCase(context: Context): UpdateItemUseCase {
        return UpdateItemUseCase(getItemRepository(context))
    }

    fun deleteItemUseCase(context: Context): DeleteItemUseCase {
        return DeleteItemUseCase(getItemRepository(context))
    }
}