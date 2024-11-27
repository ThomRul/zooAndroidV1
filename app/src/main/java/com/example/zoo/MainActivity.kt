package com.example.zoo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zoo.view.adapter.AnimalAdapter
import com.example.zoo.viewModel.ZooViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: ZooViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val recyclerView = findViewById<RecyclerView>(R.id.animalRecyclerView)
        val addAnimalButton = findViewById<Button>(R.id.addAnimalButton)
        val addAnimalForm = findViewById<LinearLayout>(R.id.addAnimalForm)

        viewModel.loginState.observe(this) { user ->
            if (user != null) {
                loginButton.visibility = View.GONE
                usernameEditText.visibility = View.GONE
                passwordEditText.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                // Affiche le bouton d'ajout uniquement pour les vétérinaires
                if (user.role == "VETERINARIAN") {
                    addAnimalButton.visibility = View.VISIBLE
                } else {
                    addAnimalButton.visibility = View.GONE
                }
            }
        }

        viewModel.animals.observe(this) { animals ->
            val adapter = AnimalAdapter(animals, viewModel.loginState.value?.role ?: "")
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            Log.d("LOGIN_DEBUG", "Attempting login with: $username/$password")

            if (username.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(username, password)
            }
        }

        addAnimalButton.setOnClickListener {
            addAnimalForm.visibility =
                if (addAnimalForm.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        findViewById<Button>(R.id.submitAnimalButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.animalNameEditText).text.toString()
            val species = findViewById<EditText>(R.id.animalSpeciesEditText).text.toString()
            val age = findViewById<EditText>(R.id.animalAgeEditText).text.toString().toIntOrNull() ?: 0
            val healthStatus = findViewById<EditText>(R.id.animalHealthEditText).text.toString()
            val keeperId = findViewById<EditText>(R.id.animalKeeperIdEditText).text.toString().toIntOrNull() ?: 0
            val diet = findViewById<EditText>(R.id.animalDietEditText).text.toString()

            if (name.isNotEmpty() && species.isNotEmpty() && healthStatus.isNotEmpty() &&
                diet.isNotEmpty() && keeperId != 0) {
                viewModel.createAnimal(name, species, age, healthStatus, keeperId.toString(), diet)
                addAnimalForm.visibility = View.GONE
            }
        }
    }
}