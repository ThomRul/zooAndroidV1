package com.example.zoo.repository

import android.util.Log
import com.example.zoo.model.Animal
import com.example.zoo.model.User
import com.example.zoo.repository.mapper.AnimalMapper
import com.example.zoo.repository.mapper.UserMapper
import com.example.zoo.repository.retrofit.RetrofitInstance

class ZooRepository {
    private val api = RetrofitInstance.api


    suspend fun login(username: String, password: String): User? {
        return try {
            val response = api.getUsers()
            if (response.isSuccessful) {
                val userDTO = response.body()?.find { dto ->
                    dto.username == username && dto.password == password
                }
                userDTO?.let { dto -> UserMapper.toUser(dto) }
            } else {
                Log.e("API_CALL", "Erreur: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            null
        }
    }

    suspend fun getUsers(): List<User> {
        return try {
            val response = api.getUsers()
            if (response.isSuccessful) {
                response.body()?.map { userDTO -> UserMapper.toUser(userDTO) } ?: emptyList()
            } else {
                Log.e("API_CALL", "Erreur: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            emptyList()
        }
    }

    suspend fun getAnimals(): List<Animal> {
        return try {
            val response = api.getAnimals()
            if (response.isSuccessful) {
                response.body()?.map { animalDTO -> AnimalMapper.toAnimal(animalDTO) } ?: emptyList()
            } else {
                Log.e("API_CALL", "Erreur: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            emptyList()
        }
    }

    suspend fun createAnimal(animal: Animal): Animal? {
        return try {
            val dto = AnimalMapper.toCreateDTO(animal)
            val response = api.createAnimal(dto)
            Log.d("API_CALL", "Response code: ${response.code()}")
            Log.d("API_CALL", "Response body: ${response.body()}")
            if (response.isSuccessful) {
                response.body()?.let { animalDTO -> AnimalMapper.toAnimal(animalDTO) }
            } else {
                Log.e("API_CALL", "Erreur: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            null
        }
    }
}