package com.example.studentsmanagerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.studentsmanagerapp.model.NivelDeEstudo
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDAO {

    // Retorna todos os alunos cadastrados.
    @Query("SELECT * FROM AlunoEntity")
    fun getAllAluno(): Flow<List<AlunoEntity>>

    // Retorna os alunos por filtro de nivel de aula.
    @Query("SELECT * FROM AlunoEntity WHERE nivelDeEstudo = :nivelDeEstudo")
    fun getAllAlunoByNivel(nivelDeEstudo: NivelDeEstudo) : Flow<List<AlunoEntity>>

    // Adiciona um novo aluno
    @Insert fun addAluno(aluno: AlunoEntity)

    // deleta o referido post-it do banco de dados
    @Delete fun deleteAluno(aluno: AlunoEntity)

    // atualiza um aluno em específico.
    @Update fun updateAluno(aluno: AlunoEntity)

}
