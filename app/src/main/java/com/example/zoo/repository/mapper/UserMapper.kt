package com.example.zoo.repository.mapper

import com.example.zoo.model.User
import com.example.zoo.repository.dto.UserDTO

object UserMapper {
    fun toUser(dto: UserDTO) = User(
        id = dto.id,
        username = dto.username,
        password = dto.password,
        role = dto.role,
        email = dto.email
    )
}
