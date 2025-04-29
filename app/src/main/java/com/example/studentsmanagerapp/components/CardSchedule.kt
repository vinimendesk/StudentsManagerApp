package com.example.studentsmanagerapp.components

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.model.DiaDaSemana

@Composable
fun CardSchecule(
    addAluno: () -> Unit,
    diaDaSemana: DiaDaSemana,
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
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
            // Adicionar Aluno.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 12.dp, end = 12.dp)
            ) {
                Button(
                    onClick = addAluno,
                    content = {
                        Text(
                            text = stringResource(R.string.adicionar_alunoDialog),
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
    }
}

@Preview
@Composable
fun CardSchedulePreview() {
    CardSchecule(addAluno = {  }, diaDaSemana = DiaDaSemana.SEGUNDAFEIRA)
}