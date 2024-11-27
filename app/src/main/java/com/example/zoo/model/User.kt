package com.example.zoo.model

class User {
    val id : String
    val username : String
    val password : String
    val role : String
    val email : String

    //création d'une personne de base (dans le cas d'un register)
    constructor(
        username: String,
        email : String,
        password : String,
    ){
        this.id = ""
        this.username = username
        this.email = email
        this.password = password
        this.role = "KEEPER"
    }

    //constructeur complet
    constructor(
        id : String,
        username : String,
        password : String,
        role : String,
        email : String
    ){
        this.id = id
        this.username = username
        this.password = password
        this.role = role
        this.email = email
    }

}