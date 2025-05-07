package com.example.studentsmanagerapp.data

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AlunoEntity(
    @PrimaryKey(autoGenerate = true)
    val AlunoId: Int = 0,
    // @DrawableRes val img: Int,
    val nome: String,
    val nivelDeEstudo: String,
    val aula: Int
)
