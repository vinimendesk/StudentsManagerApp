package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.studentsmanagerapp.components.AddButtonStudent
import com.example.studentsmanagerapp.components.AddStudentDialog
import com.example.studentsmanagerapp.components.CardEstudante
import com.example.studentsmanagerapp.components.StudyLevel
import com.example.studentsmanagerapp.components.TitleMusic
import com.example.studentsmanagerapp.data.AlunoEntity

@Composable
fun StudentsScreen(
    navController: NavController,
    studentsViewModel: StudentsViewModel = viewModel(),
    modifier: Modifier
) {
    // Coleta do UiState.
    val alunoUiState = studentsViewModel.studentsUiState.collectAsState()

    // "Desenvolvendo paixão pela música.
    Column(
        modifier = modifier
            .fillMaxSize()
            ) {
        TitleMusic(seeDescription = {}, modifier = Modifier.padding(bottom = 8.dp))
        StudyLevel(nivelAtual = alunoUiState.value.nivelAtual,
                   selecionarNivel = {
                       studentsViewModel.selecionarNivel(it)
                   }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                alunoUiState.value.filteredAlunoList.let {
                    items(it.size) { index ->
                        CardEstudante(
                            alunoEntity = alunoUiState.value.filteredAlunoList[index],
                            viewModel = studentsViewModel,
                            alunoUiState = alunoUiState.value,
                            isEditOpen = alunoUiState.value.isEditStudentDialogOpen,
                            )
                    }
                    item{Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .fillMaxWidth())}
                }
            }
            Box(modifier = Modifier.align(Alignment.BottomEnd)) {
                AddButtonStudent(
                    { studentsViewModel.openAddAlunoDialog() },
                    modifier = Modifier
                )
            }
        }

        /* CAIXAS DE DIÁLOGOS */
    }
    if (alunoUiState.value.isAddStudentDialogOpen) {
        AddStudentDialog(
            isUpdate = alunoUiState.value.isUpdate,
            valueStudent = alunoUiState.value.studentName,
            valueClass = alunoUiState.value.studentAula,
            valueNivel = alunoUiState.value.nivelDeEstudo,
            expanded = alunoUiState.value.expandedNivelDeEstudo,
            studentValueChange = {
                    newValue -> studentsViewModel.studentValueChange(newValue)
            },
            classValueChange = {
                    newValue -> studentsViewModel.classValueChange(newValue)
            },
            nivelValueChange = { nivel -> studentsViewModel.nivelValueChange(nivel) },
            onExpandChange = { newValue -> studentsViewModel.onExpandedChange(newValue) },
            onDismissRequest = { studentsViewModel.onDimissRequestAddAluno() },
            onDismissRequestMenu = { studentsViewModel.onDismissRequestMenu() },
            closeDialog = { studentsViewModel.onDimissRequestAddAluno() },
            addAluno = { studentsViewModel.addAluno(
                AlunoEntity(
                    nome = alunoUiState.value.studentName,
                    aula = alunoUiState.value.studentAula.toInt(),
                    nivelDeEstudo = alunoUiState.value.nivelDeEstudo
                )
                )
                studentsViewModel.onDimissRequestAddAluno()
            }
        )
    }
}

@Preview
@Composable
fun StudentsScreenPreview() {
    StudentsScreen(navController = rememberNavController(), modifier = Modifier.fillMaxSize())
}