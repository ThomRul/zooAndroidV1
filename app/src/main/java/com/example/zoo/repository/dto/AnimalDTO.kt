package com.example.zoo.repository.dto

data class AnimalDTO(
    val id: String,
    val name: String,
    val species: String,
    val age: Int,
    val healthStatus: String,
    val lastCheckup: String,
    val assignedKeeperId: String,
    val diet: String,
    val medicalHistory: List<MedicalRecordDTO>
)
