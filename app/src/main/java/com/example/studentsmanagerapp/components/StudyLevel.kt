package com.example.studentsmanagerapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.model.BotaoNivelDeEstudo

@Composable
fun StudyLevel(
    nivelAtual: BotaoNivelDeEstudo,
    selecionarNivel: (BotaoNivelDeEstudo) -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
        .padding(start = 15.dp)) {
        Row(
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.nivel_de_estudo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(end = 5.dp)
            )
            Image(
                painter = painterResource(R.drawable.pilha_de_livros),
                contentDescription = stringResource(R.string.pilha_de_livros),
                modifier = Modifier
                    .size(16.dp)
            )
        }
        LazyRow {
            items(BotaoNivelDeEstudo.entries) { nivel ->

                // variáveis de animação de troca de cores.
                val buttonColor by animateColorAsState(
                    targetValue = if (nivelAtual == nivel) MaterialTheme.colorScheme.error
                    else Color(0XFFE0E0E0)
                )

                val textColor by animateColorAsState(
                    targetValue = if (nivelAtual == nivel) Color.White else Color.Black
                )

                Button(
                    onClick = { selecionarNivel(nivel) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = nivel.toString(),
                        color = textColor
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun StudyLevelPreview() {
    StudyLevel(BotaoNivelDeEstudo.BASICO, { _ -> }, modifier = Modifier)
}