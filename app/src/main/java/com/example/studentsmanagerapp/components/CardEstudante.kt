package com.example.studentsmanagerapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.data.AlunoEntity
import com.example.studentsmanagerapp.screens.studentScreen.StudentsUiState
import com.example.studentsmanagerapp.screens.studentScreen.StudentsViewModel

@Composable
fun CardEstudante(
    alunoEntity: AlunoEntity,
    viewModel: StudentsViewModel,
    alunoUiState: StudentsUiState,
    isEditOpen: Boolean,
    modifier: Modifier = Modifier
)
{
    Card(
        onClick = {},
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(top = 15.dp, start = 15.dp, end = 15.dp)
    ){
        Column {
            // Botões
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {

                // Delete.
                IconButton(
                    onClick = { viewModel.deleteAluno(alunoEntity) },
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = stringResource(R.string.excluir_alunoCard),
                        tint = Color.White

                    )
                }
                // Edit.
                IconButton(
                    onClick = {
                        viewModel.switchIsUpdate()
                        viewModel.openUpdateAlunoDialog(alunoEntity)
                              },
                    modifier = Modifier
                        .size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = stringResource(R.string.editar_alunoCard),
                        tint = Color.White

                    )
                }
            }
            // Imagem do Aluno + Textos.
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // imagem do aluno.
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = stringResource(R.string.imagem_do_alunoCard),
                        tint = Color.White,
                        modifier = Modifier
                            .size(60.dp)
                    )
                    // Textos.
                    Column {
                        Text(
                            text = alunoEntity.nome,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // Texto informações.
                        Row {
                            Text(
                                text = stringResource(R.string.nivelAlunoCard, alunoEntity.nivelDeEstudo)
                            )
                            Text(
                                text = "|",
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 8.dp)
                            )
                            Text(
                                text = stringResource(R.string.aulas_dCard, alunoEntity.aula),
                            )
                        }
                    }
                }
            }
        }
        if (isEditOpen) {

            AddStudentDialog(
                isUpdate = alunoUiState.isUpdate,
                valueStudent = alunoUiState.studentName,
                valueClass = alunoUiState.studentAula,
                valueNivel = alunoUiState.nivelDeEstudo,
                expanded = alunoUiState.expandedNivelDeEstudo,
                studentValueChange = {
                        newValue -> viewModel.studentValueChange(newValue)
                },
                classValueChange = {
                        newValue -> viewModel.classValueChange(newValue)
                },
                nivelValueChange = { nivel -> viewModel.nivelValueChange(nivel) },
                onExpandChange = { newValue -> viewModel.onExpandedChange(newValue) },
                onDismissRequest = { viewModel.onDimissRequestEditAluno() },
                onDismissRequestMenu = { viewModel.onDismissRequestMenu() },
                closeDialog = { viewModel.onDimissRequestEditAluno() },
                addAluno = { viewModel.updateAluno(
                    AlunoEntity(
                        AlunoId = alunoUiState.studentId ?: 0,
                        nome = alunoUiState.studentName,
                        aula = alunoUiState.studentAula.toInt(),
                        nivelDeEstudo = alunoUiState.nivelDeEstudo
                    )
                    )
                    viewModel.onDimissRequestEditAluno()
                }
            )
        }
    }
}

@Preview
@Composable
fun CardEstudantePreview() {
    /*CardEstudante()*/
}