package com.example.zoo.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zoo.model.Animal
import com.example.zoo.model.User
import com.example.zoo.repository.ZooRepository
import kotlinx.coroutines.launch



class ZooViewModel : ViewModel() {
    private val repository = ZooRepository()

    private val _loginState = MutableLiveData<User?>()
    val loginState: LiveData<User?> = _loginState

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>> = _animals

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                repository.login(username, password)?.let { user ->
                    _loginState.value = user
                    loadAnimalsForUser(user)
                } ?: run {
                    _error.value = "Identifiants incorrects"
                }
            } catch (e: Exception) {
                _error.value = "Erreur lors de la connexion: ${e.message}"
            }
        }
    }

    private suspend fun loadAnimalsForUser(user: User) {
        val allAnimals = repository.getAnimals()
        _animals.value = when (user.role) {
            "ADMIN" -> allAnimals
            "VETERINARIAN" -> allAnimals
            "KEEPER" -> allAnimals.filter { animal ->
                animal.assignedKeeperId == user.id
            }
            else -> emptyList()
        }
    }

    fun createAnimal(name: String, species: String, age: Int, healthStatus: String,
                     assignedKeeperId: String, diet: String) {
        viewModelScope.launch {
            try {
                val currentUser = _loginState.value
                if (currentUser?.role == "VETERINARIAN") {

                    val newAnimal = Animal(
                        name = name,
                        species = species,
                        age = age,
                        healthStatus = healthStatus,
                        assignedKeeperId = assignedKeeperId,
                        diet = diet
                    )
                    repository.createAnimal(newAnimal)?.let { animal ->
                        loadAnimalsForUser(currentUser)
                    } ?: run {
                        _error.value = "Erreur lors de la création de l'animal"
                    }
                } else {
                    _error.value = "Seul un vétérinaire peut ajouter un animal"
                }
            } catch (e: Exception) {
                _error.value = "Erreur lors de la création: ${e.message}"
            }
        }
    }
}
