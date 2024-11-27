package com.example.zoo.model

import java.util.Calendar

class Animal {
    var id: String = ""
    val name: String
    val species: String
    val age: Int
    val healthStatus: String
    val lastCheckup: String
    val assignedKeeperId: String
    val diet: String
    val medicalHistory: List<MedicalRecord>

    // constructeur pour la création d'un animal
    constructor(
        name: String,
        species: String,
        age: Int,
        healthStatus: String,
        assignedKeeperId: String,
        diet: String
    ) {
        this.name = name
        this.species = species
        this.age = age
        this.healthStatus = healthStatus
        this.lastCheckup = Calendar.getInstance().time.toString()
        this.assignedKeeperId = assignedKeeperId
        this.diet = diet
        this.medicalHistory = emptyList()
    }

    //constructeur pour un animal existant
    constructor(
        id: String,
        name: String,
        species: String,
        age: Int,
        healthStatus: String,
        lastCheckup: String,
        assignedKeeperId: String,
        diet: String,
        medicalHistory: List<MedicalRecord>
    ) {
        this.id = id
        this.name = name
        this.species = species
        this.age = age
        this.healthStatus = healthStatus
        this.lastCheckup = lastCheckup
        this.assignedKeeperId = assignedKeeperId
        this.diet = diet
        this.medicalHistory = medicalHistory
    }

}