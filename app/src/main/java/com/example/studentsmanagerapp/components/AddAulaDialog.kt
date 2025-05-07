@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.studentsmanagerapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.data.AlunoEntity
import com.example.studentsmanagerapp.model.BotaoNivelDeEstudo
import com.example.studentsmanagerapp.model.NivelDeEstudo
import com.example.studentsmanagerapp.screens.studentScreen.ScheduleViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAulaDialog(
    valueHorario: TextFieldValue,
    horarioValueChange: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    closeDialog: () -> Unit,
    addAluno: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(0XFFF5A623))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 4.dp)
            ) {
                IconButton(
                    onClick = closeDialog,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.fechar_tela_adicionar_aluno),
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Campo de texto para inserir o horario da aula
            TextField(
                value = valueHorario,
                onValueChange = { newValue -> horarioValueChange(newValue) },
                label = { Text(text = stringResource(R.string.hor_rio_da_aulaTxt), fontWeight = FontWeight.Bold) },
                placeholder = { Text(text = stringResource(R.string.insira_o_hor_rio_da_aulaTxt)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, end = 12.dp)
            ) {
                Button(
                    onClick = addAluno,
                    content = {
                        Text(
                            text = stringResource(R.string.adicionar_aulaTxt),
                            fontWeight = FontWeight.Bold
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
    }
}

@Composable
fun AddAulaAlunoDialog(
    addAluno: () -> Unit,
    alunoListFiltered: List<AlunoEntity>,
    valueNivel: String,
    valueAluno: String,
    nivelValueChange: (String) -> Unit,
    alunoValueChange: (String) -> Unit,
    expanded: Boolean,
    expandedAluno: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onExpandChangeAluno: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onDismissRequestMenu: () -> Unit,
    onDismissRequestMenuAluno: () -> Unit,
    closeDialog: () -> Unit,
    modifier: Modifier,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .height(300.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color(0XFFF5A623))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 4.dp)
            ) {
                IconButton(
                    onClick = closeDialog,
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.fechar_tela_adicionar_aluno),
                        tint = Color.White
                    )
                }
            }

            // Campo de texto para inserir o nível de estudo.
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = onExpandChange,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                TextField(
                    value = valueNivel,
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        if (expanded) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Botão para baixo"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Botão para baixo"
                            )
                        }
                    },
                    shape = RoundedCornerShape(size = 15.dp),
                    label = {
                        Text(
                            text = stringResource(R.string.selecioine_o_nivel_de_estudoDialog),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = onDismissRequestMenu
                ) {
                    BotaoNivelDeEstudo.entries.forEach { nivel ->
                        DropdownMenuItem(
                            text = { Text(text = nivel.name) },
                            onClick = {
                                nivelValueChange(nivel.name)
                                onExpandChange(false)
                            },
                        )
                    }
                }
            }
            // Campo de texto para inserir o aluno.
            ExposedDropdownMenuBox(
                expanded = expandedAluno,
                onExpandedChange = onExpandChangeAluno,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                TextField(
                    value = valueAluno,
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        if (expandedAluno) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = "Botão para cima"
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "Botão para baixo"
                            )
                        }
                    },
                    shape = RoundedCornerShape(size = 15.dp),
                    label = {
                        Text(
                            text = stringResource(R.string.adicionar_alunoDialog),
                            fontWeight = FontWeight.Bold
                        )
                    },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedAluno,
                    onDismissRequest = onDismissRequestMenuAluno
                ) {
                    alunoListFiltered.forEach { aluno ->
                        DropdownMenuItem(
                            text = { Text(text = aluno.nome) },
                            onClick = {
                                alunoValueChange(aluno.nome)
                                onExpandChangeAluno(false)
                            },
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, end = 12.dp)
            ) {
                Button(
                    onClick = addAluno,
                    content = {
                        Text(
                            text = stringResource(R.string.adicionar_alunoDialog),
                            fontWeight = FontWeight.Bold
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
    }
}

@Preview
@Composable
fun AddAulaDialogPreview() {
    val scheduleViewModel: ScheduleViewModel = viewModel()
    val scheduleUiState = scheduleViewModel.scheduleUiState.collectAsState()

    /* AddHorarioDialog(
        isUpdate = scheduleUiState.value.isUpdate,
        valueStudent = scheduleUiState.value.studentName,
        studentValueChange = { newValue -> scheduleViewModel.studentValueChange(newValue) },
        onDismissRequest = { scheduleViewModel.onDimissRequestAddAluno() },
        onDismissRequestMenu = { scheduleViewModel.onDismissRequestMenu() },
        closeDialog = { scheduleViewModel.onDimissRequestAddAluno() },
        addAluno = { scheduleViewModel.addAluno(
            AlunoEntity(
                nome = scheduleUiState.value.studentName,
                aula = scheduleUiState.value.studentAula.toInt(),
                nivelDeEstudo = scheduleUiState.value.nivelDeEstudo
            )
            )
        }
    ) */
}