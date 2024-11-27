package com.example.zoo.repository.mapper

import com.example.zoo.model.Animal
import com.example.zoo.model.MedicalRecord
import com.example.zoo.repository.dto.AnimalDTO
import com.example.zoo.repository.dto.CreateAnimalDTO
import com.example.zoo.repository.dto.MedicalRecordDTO

object AnimalMapper {
    fun toAnimal(dto: AnimalDTO) = Animal(
        id = dto.id,
        name = dto.name,
        species = dto.species,
        age = dto.age,
        healthStatus = dto.healthStatus,
        lastCheckup = dto.lastCheckup,
        assignedKeeperId = dto.assignedKeeperId,
        diet = dto.diet,
        medicalHistory = dto.medicalHistory.map { medicalRecord ->
            MedicalRecord(
                date = medicalRecord.date,
                treatment = medicalRecord.treatment,
                vetId = medicalRecord.vetId
            )
        }
    )

    fun toCreateDTO(animal: Animal) = CreateAnimalDTO(
        name = animal.name,
        species = animal.species,
        age = animal.age,
        healthStatus = animal.healthStatus,
        lastCheckup = animal.lastCheckup,
        assignedKeeperId = animal.assignedKeeperId,
        diet = animal.diet,
        medicalHistory = animal.medicalHistory.map { record ->
            MedicalRecordDTO(
                date = record.date,
                treatment = record.treatment,
                vetId = record.vetId
            )
        }
    )
}