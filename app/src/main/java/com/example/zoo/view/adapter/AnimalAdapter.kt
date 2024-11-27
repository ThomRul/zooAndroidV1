package com.example.zoo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zoo.R
import com.example.zoo.model.Animal
import com.example.zoo.view.viewHolder.AnimalViewHolder

class AnimalAdapter(private val animals: List<Animal>, private val userRole: String) : RecyclerView.Adapter<AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.nameTextView.text = animal.name
        holder.speciesTextView.text = animal.species
        holder.healthStatusTextView.text = animal.healthStatus
        holder.lastCheckupTextView.text = animal.lastCheckup

        if (userRole == "VETERINARIAN") {
            holder.medicalHistoryTextView.visibility = View.VISIBLE
            val medicalHistory = if (animal.medicalHistory.isNotEmpty()) {
                animal.medicalHistory.joinToString("\n") { record ->
                    "Date: ${record.date}, Treatment: ${record.treatment}"
                }
            } else {
                "No medical history"
            }
            holder.medicalHistoryTextView.text = medicalHistory
        } else {
            holder.medicalHistoryTextView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = animals.size
}