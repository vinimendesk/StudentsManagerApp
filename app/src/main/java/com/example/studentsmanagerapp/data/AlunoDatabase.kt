package com.example.studentsmanagerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlunoEntity::class], version = 1)
abstract class AlunoDatabase: RoomDatabase() {
    abstract fun getAlunoDAO(): AlunoDAO
}