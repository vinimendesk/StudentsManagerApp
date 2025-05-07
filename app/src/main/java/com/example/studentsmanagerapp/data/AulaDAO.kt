package com.example.studentsmanagerapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.studentsmanagerapp.model.DiaDaSemana
import kotlinx.coroutines.flow.Flow

@Dao
interface AulaDAO {

    // Função para inserir uma nova aula.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAula(aula: AulaEntity): Long

    // Função para deletar uma aula.
    @Delete
    fun deleteAula(aula: AulaEntity)

    // Função para inserir um novo relacionamento aula e aluno.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlunoAulaCrossRef(crossRef: AlunoAulaCrossRef)

    // Retorna todas as aulas.
    @Query("SELECT * FROM AulaEntity")
    fun getAllAula(): Flow<List<AulaEntity>>

    // Retorna todos o aluno com o nome específico.
    @Query("SELECT * FROM AlunoEntity WHERE nome = :nomeAluno")
    fun getAlunoByName(nomeAluno: String): AlunoEntity

//    // Retorna todas as aulas por dia em específico;
//    @Query("SELECT * FROM AulaEntity WHERE diaDaSemana = :diaDaSemana")
//    fun buscarAlunoPorAula(diaDaSemana: String): Flow<List<AulaComAluno>>

}