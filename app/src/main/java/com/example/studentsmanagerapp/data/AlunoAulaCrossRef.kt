package com.example.studentsmanagerapp.data

import androidx.room.Entity

@Entity(primaryKeys = ["AlunoId", "AulaId"])
data class AlunoAulaCrossRef(
    val AlunoId: Int,
    val AulaId: Int
)