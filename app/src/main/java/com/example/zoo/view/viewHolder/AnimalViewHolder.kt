package com.example.zoo.view.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zoo.R

class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    val speciesTextView: TextView = itemView.findViewById(R.id.speciesTextView)
    val healthStatusTextView: TextView = itemView.findViewById(R.id.healthStatusTextView)
    val lastCheckupTextView: TextView = itemView.findViewById(R.id.lastCheckupTextView)
    val medicalHistoryTextView: TextView = itemView.findViewById(R.id.medicalHistoryTextView)
}