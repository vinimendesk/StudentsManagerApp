package com.example.studentsmanagerapp.screens.studentScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsmanagerapp.MainApplication
import com.example.studentsmanagerapp.components.NivelDeEstudo
import com.example.studentsmanagerapp.data.AlunoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentsViewModel: ViewModel() {

    // Faz referência a UiState como estado mútavel para edição.
    private val _studentsUiState = MutableStateFlow(StudentsUiState())
    // Armazena o estado coletado como imputavel para exibição.
    val studentsUiState = _studentsUiState.asStateFlow()

    // Obtém o DAO referente ao banco de dados.
    val alunoDao = MainApplication.alunoDatabase.getAlunoDAO()
    // Coleta todos os alunos registrados no banco de dados.

    private val _alunoList = MutableStateFlow<List<AlunoEntity>>(emptyList())
    val alunoList = _alunoList.asStateFlow()

    init {
        loadAllAluno()
    }

    fun loadAllAluno() {
        viewModelScope.launch(Dispatchers.IO) {
            alunoDao.getAllAluno().collect { alunos ->
                _alunoList.value = alunos
                filterAlunosByNivel(studentsUiState.value.nivelAtual)
            }
        }
    }

    fun filterAlunosByNivel(nivelDeEstudo: NivelDeEstudo) {
        val currentList = _alunoList.value
        _studentsUiState.update {
            it.copy(
                filteredAlunoList = when (nivelDeEstudo) {
                    NivelDeEstudo.TODOS -> currentList
                    else -> currentList.filter { it.nivelDeEstudo == nivelDeEstudo.name }
                }
            )
        }
    }

    fun selecionarNivel(nivel: NivelDeEstudo) {
        _studentsUiState.update {
            it.copy(nivelAtual = nivel)
        }
        filterAlunosByNivel(nivel)
    }

    /* Funções DAO */
    // Adiciona um novo aluno ao banco de dados.
    fun addAluno(aluno: AlunoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            alunoDao.addAluno(aluno = aluno)
        }
    }

    // Exclui aluno ao banco de dados.
    fun deleteAluno(aluno: AlunoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            alunoDao.deleteAluno(aluno = aluno)
        }
    }

    // Atualiza um novo aluno ao banco de dados.
    fun updateAluno(aluno: AlunoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            alunoDao.updateAluno(aluno = aluno)
        }
    }

    /* FUNÇÕES ADD STUDENT DIALOG */
    fun studentValueChange(student: String) {
        _studentsUiState.update {
            it.copy(studentName = student)
        }
    }

    fun classValueChange(numberOfClass: String) {
        _studentsUiState.update {
            it.copy(studentAula = numberOfClass)
        }
    }

    fun nivelValueChange(nivel: String) {
        _studentsUiState.update {
            it.copy(nivelDeEstudo = nivel)
        }
    }

    fun onExpandedChange(newValue: Boolean) {
        _studentsUiState.update {
            it.copy(expandedNivelDeEstudo = newValue)
        }
    }


    fun onDimissRequestAddAluno() {
        _studentsUiState.update {
            it.copy(isAddStudentDialogOpen = false)
        }
        _studentsUiState.update {
            it.copy(
                studentName = "",
                nivelDeEstudo = "",
                studentAula = "",
            )
        }
    }

    fun onDimissRequestEditAluno() {
        _studentsUiState.update {
            it.copy(isEditStudentDialogOpen = false)
        }
        _studentsUiState.update {
            it.copy(
                studentName = "",
                nivelDeEstudo = "",
                studentAula = "",
            )
        }
    }

    fun onDismissRequestMenu() {
        _studentsUiState.update {
            it.copy(expandedNivelDeEstudo = false)
        }
    }

    fun openAddAlunoDialog() {
        _studentsUiState.update {
            it.copy(
                isAddStudentDialogOpen = true,
            )
        }
    }

    fun openUpdateAlunoDialog(aluno: AlunoEntity) {
        _studentsUiState.update {
            it.copy(
                isEditStudentDialogOpen = true,
                studentId = aluno.id,
                studentName = aluno.nome,
                studentAula = aluno.aula.toString(),
                nivelDeEstudo = aluno.nivelDeEstudo
            )
        }
    }

    fun switchIsUpdate() {
        val isUpdate = studentsUiState.value.isUpdate
        _studentsUiState.update {
            it.copy(isUpdate = !isUpdate)
        }
    }
}