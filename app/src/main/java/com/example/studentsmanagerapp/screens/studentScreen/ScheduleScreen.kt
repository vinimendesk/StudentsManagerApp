package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.components.CardSchecule
import com.example.studentsmanagerapp.model.DiaDaSemana

@Composable
fun ScheduleScreen(
    scheduleViewModel: ScheduleViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val scheduleUiState = scheduleViewModel.scheduleUiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
    ) {
        // Agenda de Aulas.
        Text(
            text = stringResource(R.string.agenda_de_aulas),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        // Cards da Semana.
        LazyRow {
            items(DiaDaSemana.entries) { dia ->
                val filteredListAulaEntity = scheduleUiState.value.listAulaEntity.filter {
                    it.diaDaSemana == dia.dia
                }
                CardSchecule(
                    scheduleViewModel = scheduleViewModel,
                    scheduleUiState = scheduleUiState.value,
                    listAulaEntity = filteredListAulaEntity,
                    isAddAulaOpen = scheduleUiState.value.isAddAulaOpenDialog,
                    isAddAlunoAulaOpen = scheduleUiState.value.isAddAulaAlunoOpenDialog,
                    diaDaSemana = dia,
                    modifier = Modifier
                )
            }
        }
        // Card Rendimento Semanal.
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF50E3C2)),
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = 15.dp, end = 15.dp, bottom = 8.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.rendimento_semanal),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Spacer(Modifier.width(40.dp))
                Text(
                    text = "R$ 250,00",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

        }
    }
}

@Preview
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen(navController = rememberNavController())
}