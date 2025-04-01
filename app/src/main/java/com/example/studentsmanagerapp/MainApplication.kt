package com.example.studentsmanagerapp

import android.app.Application
import androidx.room.Room
import com.example.studentsmanagerapp.data.AlunoDatabase

// inicializa o banco de dados dentro do contexto do aplicativo.
class MainApplication: Application() {
    // objeto interno definido para utilizar o banco de dados interno.
    companion object {
        lateinit var alunoDatabase: AlunoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        alunoDatabase = Room.databaseBuilder(
            applicationContext,
            AlunoDatabase::class.java,
            "aluno-database"
        ).build()
    }
}