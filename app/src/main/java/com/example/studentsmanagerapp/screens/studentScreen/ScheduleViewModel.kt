package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentsmanagerapp.MainApplication
import com.example.studentsmanagerapp.data.AlunoAulaCrossRef
import com.example.studentsmanagerapp.data.AlunoEntity
import com.example.studentsmanagerapp.data.AulaEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel: ViewModel() {

    // Faz referência a UiState como estado mútavel para edição.
    private val _scheduleUiState = MutableStateFlow(ScheduleUiState())

    // Armazena o estado coletado como imputavel para exibição.
    val scheduleUiState = _scheduleUiState.asStateFlow()

    // Obtém o DAO referente ao banco de dados.
    val aulaDao = MainApplication.alunoDatabase.getAulaDAO()

    /* Funções DAO */
    // Adiciona um novo aluno ao banco de dados.
    fun addAula(aula: AulaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            aulaDao.addAula(aula = aula)
        }
    }

    fun deleteAula(aula: AulaEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            aulaDao.deleteAula(aula = aula)
        }
    }

    fun addAlunoAulaCrossRef(crossRef: AlunoAulaCrossRef) {
        viewModelScope.launch(Dispatchers.IO) {
            aulaDao.addAlunoAulaCrossRef(crossRef = crossRef)
        }
    }

    fun getAlunoByName(nome: String, onResult: (AlunoEntity) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val aluno = aulaDao.getAlunoByName(nome)
            onResult(aluno)
        }
    }

    // Coleta todos os alunos registrados no banco de dados.
    private val _aulaList = MutableStateFlow<List<AulaEntity>>(emptyList())
    val aulaList = _aulaList.asStateFlow()

    // Obtém o DAO referente ao banco de dados.
    val alunoDao = MainApplication.alunoDatabase.getAlunoDAO()

    // Coleta todos os alunos registrados no banco de dados.
    private val _alunoList = MutableStateFlow<List<AlunoEntity>>(emptyList())
    val alunoList = _alunoList.asStateFlow()

    init {
        loadAllAula()
        loadAllAluno()
    }

    fun loadAllAula() {
        viewModelScope.launch(Dispatchers.IO) {
            aulaDao.getAllAula().collect { aulas ->
                _aulaList.value = aulas
                _scheduleUiState.update {
                    it.copy(
                        listAulaEntity = _aulaList.value
                    )
                }
            }
        }
    }

    fun loadAllAluno() {
        viewModelScope.launch(Dispatchers.IO) {
            alunoDao.getAllAluno().collect { alunos ->
                _alunoList.value = alunos
                _scheduleUiState.update {
                    it.copy(filteredAlunoList = _alunoList.value)
                }
            }
        }
    }

    /* FUNÇÕES ADD AULA DIALOG */
    fun horarioValueChange(horario: TextFieldValue) {
        val horarioFormatado = formatarHorario(horario)
        _scheduleUiState.update {
            it.copy(horarioAula = TextFieldValue(
                text = horarioFormatado,
                selection = TextRange(horarioFormatado.length))
            )
        }
    }

    // Função para formatar o texto como HH:MM
    fun formatarHorario(input: TextFieldValue): String {
        // Remove qualquer caractere que não seja dígito
        val digits = input.text.filter { it.isDigit() }.take(4) // Máximo de 4 dígitos
        return when (digits.length) {
            0, 1, 2 -> digits
            3 -> "${digits.substring(0, 2)}:${digits[2]}"
            else -> "${digits.substring(0, 2)}:${digits.substring(2, 4)}"
        }
    }

    fun nivelValueChange(nivel: String) {
        _scheduleUiState.update {
            it.copy(nivelDeEstudo = nivel)
        }
    }

    fun alunoValueChange(aluno: String) {
        getAlunoByName(aluno) { alunoResult ->
            _scheduleUiState.update {
                it.copy(aluno = alunoResult)
            }
        }
    }

    fun onExpandedChange(newValue: Boolean) {
        _scheduleUiState.update {
            it.copy(expandedNivelDeEstudo = newValue)
        }
    }

    fun onExpandedChangeAluno(newValue: Boolean) {
        _scheduleUiState.update {
            it.copy(expandedAluno = newValue)
        }
    }

    fun openAddAulaDialog() {
        _scheduleUiState.update {
            it.copy(
                isAddAulaOpenDialog = true,
            )
        }
    }

    fun openAddAlunoAulaDialog(aula: AulaEntity) {
        _scheduleUiState.update {
            it.copy(
                isAddAulaAlunoOpenDialog = true,
                aula = aula
            )
        }
    }

    fun onDimissRequestAula() {
        _scheduleUiState.update {
            it.copy(
                isAddAulaOpenDialog = false,
                horarioAula = TextFieldValue("")
            )
        }
    }

    fun onDimissRequestAlunoAula() {
        _scheduleUiState.update {
            it.copy(
                isAddAulaAlunoOpenDialog = false,
                nivelDeEstudo = "",
                aluno = null
            )
        }
    }

    fun onDismissRequestMenu() {
        _scheduleUiState.update {
            it.copy(expandedNivelDeEstudo = false)
        }
    }

    fun onDismissRequestMenuAluno() {
        _scheduleUiState.update {
            it.copy(expandedAluno = false)
        }
    }

}