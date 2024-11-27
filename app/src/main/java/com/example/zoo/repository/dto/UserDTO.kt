package com.example.zoo.repository.dto

data class UserDTO(
    val id: String,
    val username: String,
    val password: String,
    val role: String,
    val email: String
)
