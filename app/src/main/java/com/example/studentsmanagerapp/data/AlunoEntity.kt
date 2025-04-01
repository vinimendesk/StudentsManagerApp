package com.example.studentsmanagerapp.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studentsmanagerapp.components.NivelDeEstudo

@Entity
data class AlunoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // @DrawableRes val img: Int,
    val nome: String,
    val nivelDeEstudo: String,
    val aula: Int
)
