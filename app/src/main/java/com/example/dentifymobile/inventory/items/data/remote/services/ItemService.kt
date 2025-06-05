package com.example.dentifymobile.inventory.items.data.remote.services

import com.example.dentifymobile.inventory.items.data.model.ItemResponse
import com.example.dentifymobile.inventory.items.data.remote.dto.ItemRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ItemService {
    @GET("v1/items")
    suspend fun getAllItems(): Response<List<ItemResponse>>

    @POST("v1/items")
    suspend fun createItem(@Body itemRequest: ItemRequest): Response<ItemResponse>

    @PUT("v1/items/{id}")
    suspend fun updateItem(
        @Path("id") id: Long,
        @Body itemRequest: ItemRequest
    ): Response<ItemResponse>

    @DELETE("v1/items/{id}")
    suspend fun deleteItem(@Path("id") id: Long): Response<Void>
}