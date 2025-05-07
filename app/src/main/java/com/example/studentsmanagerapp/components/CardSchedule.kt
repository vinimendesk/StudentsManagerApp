package com.example.studentsmanagerapp.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.data.AlunoAulaCrossRef
import com.example.studentsmanagerapp.data.AulaEntity
import com.example.studentsmanagerapp.model.DiaDaSemana
import com.example.studentsmanagerapp.screens.studentScreen.ScheduleUiState
import com.example.studentsmanagerapp.screens.studentScreen.ScheduleViewModel
import com.example.studentsmanagerapp.screens.studentScreen.StudentsViewModel

@Composable
fun CardSchecule(
    scheduleViewModel: ScheduleViewModel,
    scheduleUiState: ScheduleUiState,
    listAulaEntity: List<AulaEntity>,
    diaDaSemana: DiaDaSemana,
    isAddAulaOpen: Boolean,
    isAddAlunoAulaOpen: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9D9D9)),
        modifier = Modifier
            .width(400.dp)
            .height(500.dp)
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Dia da Semana.
            Text(
                text = diaDaSemana.dia,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            )
            HorarioAula(scheduleViewModel ,listAulaEntity, modifier = Modifier)
            // Adicionar Aluno.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp, end = 12.dp)
            ) {
                Button(
                    onClick = { scheduleViewModel.openAddAulaDialog() },
                    content = {
                        Text(
                            text = stringResource(R.string.adicionar_aulaCardSchedule),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0XFF50E3C2)),
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .border(
                            width = 4.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(40.dp)
                        )
                )
            }
        }

        if(isAddAulaOpen) {

            AddAulaDialog(
                horarioValueChange = { newValue -> scheduleViewModel.horarioValueChange(newValue) },
                valueHorario = scheduleUiState.horarioAula,
                closeDialog = { scheduleViewModel.onDimissRequestAula() },
                onDismissRequest = { scheduleViewModel.onDimissRequestAula() },
                addAluno = {
                    scheduleViewModel.addAula(
                        AulaEntity(
                            diaDaSemana = diaDaSemana.dia.toString(),
                            horario = scheduleUiState.horarioAula.text.toString()
                        )
                    )
                    scheduleViewModel.onDimissRequestAula()
                }
            )

        }

        if (isAddAlunoAulaOpen) {
            AddAulaAlunoDialog(
                alunoListFiltered = scheduleUiState.filteredAlunoList,
                valueNivel = scheduleUiState.nivelDeEstudo,
                valueAluno = scheduleUiState.aluno?.nome ?: "",
                nivelValueChange = { nivel ->
                    scheduleViewModel.nivelValueChange(nivel)
                                   },
                alunoValueChange = { aluno -> scheduleViewModel.alunoValueChange(aluno) },
                expanded = scheduleUiState.expandedNivelDeEstudo,
                expandedAluno = scheduleUiState.expandedAluno,
                onExpandChange = { newValue ->
                    scheduleViewModel.onExpandedChange(newValue)
                },
                onExpandChangeAluno = { newValue -> scheduleViewModel.onExpandedChangeAluno(newValue) },
                closeDialog = { scheduleViewModel.onDimissRequestAlunoAula() },
                onDismissRequest = { scheduleViewModel.onDimissRequestAlunoAula() },
                onDismissRequestMenu = { scheduleViewModel.onDismissRequestMenu() },
                onDismissRequestMenuAluno = { scheduleViewModel.onDismissRequestMenuAluno() },
                addAluno = {
                    if (scheduleUiState.aluno != null && scheduleUiState.aula != null) {
                        scheduleViewModel.addAlunoAulaCrossRef(
                            crossRef = AlunoAulaCrossRef(
                                AlunoId = scheduleUiState.aluno.AlunoId,
                                AulaId = scheduleUiState.aula.AulaId,
                            )
                        )
                        scheduleViewModel.onDimissRequestAlunoAula()
                    } else {

                    }
                },
                modifier = Modifier
            )
        }
    }
}

@Composable
fun HorarioAula(
    scheduleViewModel: ScheduleViewModel,
    listAulaEntity: List<AulaEntity>,
    modifier: Modifier) {

    LazyColumn {
        items(listAulaEntity.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier
                        .width(350.dp)
                        .heightIn(min = 100.dp)
                        .padding(bottom = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        // Botões
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, end = 8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                text = listAulaEntity[index].horario,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            )
                            // Adicionar Aluno.
                            IconButton(
                                onClick = { scheduleViewModel.openAddAlunoAulaDialog(listAulaEntity[index]) },
                                modifier = Modifier
                                    .size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = stringResource(R.string.adicionar_alunoDialog),
                                    tint = Color.Black
                                )
                            }
                            // Delete.
                            IconButton(
                                onClick = { scheduleViewModel.deleteAula(listAulaEntity[index]) },
                                modifier = Modifier
                                    .size(30.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = stringResource(R.string.excluir_alunoCard),
                                    tint = Color.Black
                                )
                            }
                        }
                        // LazyColumn com os alunos
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardSchedulePreview() {
    // CardSchecule(listAulaEntity = emptyList() , isAddStudentOpen = false, scheduleUiState = , diaDaSemana = DiaDaSemana.SEGUNDAFEIRA)
}