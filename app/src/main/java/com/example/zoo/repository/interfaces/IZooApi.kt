package com.example.zoo.repository.interfaces

import com.example.zoo.repository.dto.AnimalDTO
import com.example.zoo.repository.dto.CreateAnimalDTO
import com.example.zoo.repository.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IZooApi {
    @GET("users")
    suspend fun getUsers(): Response<List<UserDTO>>

    @GET("animals")
    suspend fun getAnimals(): Response<List<AnimalDTO>>

    @POST("animals")
    suspend fun createAnimal(@Body animal: CreateAnimalDTO): Response<AnimalDTO>

}