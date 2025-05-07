package com.example.studentsmanagerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AulaEntity(
    @PrimaryKey(autoGenerate = true)
    val AulaId: Int = 0,
    val horario: String,
    val diaDaSemana: String
)
