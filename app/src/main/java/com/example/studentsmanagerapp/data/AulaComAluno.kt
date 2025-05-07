package com.example.studentsmanagerapp.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AulaComAluno(
    // Busca todos os campos da tabela Aula para a consulta JOIN.
    @Embedded val aula: AulaEntity,
    // Faz o INNERJOIN da tabela Aula com AlunoAulaCrossRef
    @Relation(
        parentColumn = "AulaId",
        entityColumn = "AlunoId",
        associateBy = Junction(
            value = AlunoAulaCrossRef::class,
            parentColumn = "AulaId",
            entityColumn = "AlunoId"
        )
    )
    // Tabela final que busco após o JOIN.
    val alunos: List<AlunoEntity>
)