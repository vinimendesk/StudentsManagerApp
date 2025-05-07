package com.example.studentsmanagerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    AlunoEntity::class,
    AulaEntity::class,
    AlunoAulaCrossRef::class],
    version = 2)
abstract class AlunoDatabase: RoomDatabase() {
    abstract fun getAlunoDAO(): AlunoDAO
    abstract fun getAulaDAO(): AulaDAO
}