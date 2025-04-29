package com.example.studentsmanagerapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.data.AlunoEntity
import com.example.studentsmanagerapp.model.NivelDeEstudo
import com.example.studentsmanagerapp.screens.studentScreen.StudentsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentDialog(
    isUpdate: Boolean,
    valueStudent: String,
    valueClass: String,
    valueNivel: String,
    classValueChange: (String) -> Unit,
    studentValueChange: (String) -> Unit,
    nivelValueChange: (String) -> Unit,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    onDismissRequestMenu: () -> Unit,
    closeDialog: () -> Unit,
    addAluno: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier
                .height(400.dp)
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

            // Campo de texto para inserir o nome do aluno
            TextField(
                value = valueStudent,
                onValueChange = { newValue -> studentValueChange(newValue) },
                label = { Text(text = stringResource(R.string.nome_do_alunoDialog), fontWeight = FontWeight.Bold) },
                placeholder = { Text(text = stringResource(R.string.Dialog)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Campo de texto para inserir o nível de estudo.
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = onExpandChange,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                TextField(
                    value = valueNivel,
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = { if(expanded) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Botão para baixo"
                        )
                                  } else { Icon (
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Botão para baixo"
                    ) }
                                  },
                    shape = RoundedCornerShape(size = 15.dp),
                    label = { Text(text = stringResource(R.string.selecioine_o_nivel_de_estudoDialog), fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = onDismissRequestMenu
                ) {
                    NivelDeEstudo.entries.forEach { nivel->
                        DropdownMenuItem(
                            text = { Text(text = nivel.name) },
                            onClick = { nivelValueChange(nivel.name)
                                        onExpandChange(false)
                                      },
                        )
                    }
                }
            }

            // Campo de texto para inserir as aulas do aluno.
            TextField(
                value = valueClass,
                onValueChange = { newValue -> classValueChange(newValue) },
                label = { Text(text = stringResource(R.string.quantidade_de_aulasDialog), fontWeight = FontWeight.Bold) },
                placeholder = { Text(text = stringResource(R.string.insira_a_quantidade_de_aulasDialog)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, end = 12.dp)
            ) {
                Button(
                    onClick = addAluno,
                    content = {
                        Text(
                            text = if (isUpdate) stringResource(R.string.atualizar_alunoCard)
                                    else stringResource(R.string.adicionar_alunoDialog),
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
fun AddStudentDialogPreview() {
    val studentViewModel: StudentsViewModel = viewModel()
    val alunoUiState = studentViewModel.studentsUiState.collectAsState()

    AddStudentDialog(
        isUpdate = alunoUiState.value.isUpdate,
        valueStudent = alunoUiState.value.studentName,
        valueClass = alunoUiState.value.studentAula,
        valueNivel = alunoUiState.value.nivelDeEstudo,
        studentValueChange = { newValue -> studentViewModel.studentValueChange(newValue) },
        classValueChange = { newValue -> studentViewModel.classValueChange(newValue) },
        nivelValueChange = { nivel -> studentViewModel.nivelValueChange(nivel) },
        expanded = alunoUiState.value.expandedNivelDeEstudo,
        onExpandChange = { newValue -> studentViewModel.onExpandedChange(newValue) } ,
        onDismissRequest = { studentViewModel.onDimissRequestAddAluno() },
        onDismissRequestMenu = { studentViewModel.onDismissRequestMenu() },
        closeDialog = { studentViewModel.onDimissRequestAddAluno() },
        addAluno = { studentViewModel.addAluno(
            AlunoEntity(
                nome = alunoUiState.value.studentName,
                aula = alunoUiState.value.studentAula.toInt(),
                nivelDeEstudo = alunoUiState.value.nivelDeEstudo
            )
            )
        }
    )
}