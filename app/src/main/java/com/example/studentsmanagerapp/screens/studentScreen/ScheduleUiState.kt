package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.ui.text.input.TextFieldValue
import com.example.studentsmanagerapp.data.AlunoEntity
import com.example.studentsmanagerapp.data.AulaEntity

data class ScheduleUiState (
    // Lista com os dados dos alunos.
    val filteredAlunoList: List<AlunoEntity> = emptyList(),
    // Lista de aulas.
    val listAulaEntity: List<AulaEntity> = emptyList(),
    // nivel de estudo na caixa de dialog.
    val nivelDeEstudo: String = "",
    // aluno na caixa dialog.
    val aluno: AlunoEntity? = null,
    // aula para ser adicionada junto com o aluno na caixa dialog.
    val aula: AulaEntity? = null,
    // verifica se o menu de nivel de estudo está expandido.
    val expandedNivelDeEstudo: Boolean = false,
    // verifica se o menu de aluno está expandido.
    val expandedAluno: Boolean = false,
    val isAddAulaOpenDialog: Boolean = false,
    val isAddAulaAlunoOpenDialog: Boolean = false,
    val horarioAula: TextFieldValue = TextFieldValue(""),
)