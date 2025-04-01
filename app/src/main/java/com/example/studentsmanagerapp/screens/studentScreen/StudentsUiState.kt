package com.example.studentsmanagerapp.screens.studentScreen

import com.example.studentsmanagerapp.components.NivelDeEstudo
import com.example.studentsmanagerapp.data.AlunoEntity

data class StudentsUiState(
    // Lista com os dados dos alunos.
    val filteredAlunoList: List<AlunoEntity> = emptyList(),
    // filtro de alunos por nível.
    val nivelAtual: NivelDeEstudo = NivelDeEstudo.TODOS,
    // aluno id para busca.
    val studentId: Int? = null,
    // aluno na caixa de diálog.
    val studentName: String = "",
    // quantidade de aulas na caixa de dialog.
    val studentAula: String = "",
    // nivel de estudo na caixa de dialog.
    val nivelDeEstudo: String = "",
    // verifica se a tela de adicionar aluno está aberta.
    val isAddStudentDialogOpen: Boolean = false,
    // verifica se a tela de editar aluno foi acionada.
    val isEditStudentDialogOpen: Boolean = false,
    // verifica se o menu de nivel de estudo está expandido.
    val expandedNivelDeEstudo: Boolean = false,
    // verifica se a caixa de diálogo é add ou update
    val isUpdate: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null

)