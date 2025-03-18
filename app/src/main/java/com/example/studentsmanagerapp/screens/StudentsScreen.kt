package com.example.studentsmanagerapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentsmanagerapp.components.NivelDeEstudo
import com.example.studentsmanagerapp.components.StudyLevel
import com.example.studentsmanagerapp.components.TitleMusic

@Composable
fun StudentsScreen(modifier: Modifier) {

    var nivelAtual by remember {mutableStateOf(NivelDeEstudo.INICIANTE)}

    // "Desenvolvendo paixão pela música.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()) {
        TitleMusic(seeDescription = {}, modifier = Modifier.padding(bottom = 8.dp))
        StudyLevel(nivelAtual = nivelAtual, selecionarNivel = { nivel -> nivelAtual = nivel}, modifier = Modifier)
    }
}

@Preview
@Composable
fun StudentsScreenPreview() {
    StudentsScreen(modifier = Modifier.fillMaxSize())
}