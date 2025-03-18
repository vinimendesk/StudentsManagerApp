package com.example.studentsmanagerapp.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentsmanagerapp.R

@Composable
fun TitleMusic(seeDescription: () -> Unit, modifier: Modifier) {
    Card(
        onClick = seeDescription,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.error),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f),
                contentAlignment = Alignment.Center // Centraliza o conteúdo do Box
            ) {
                // // Texto "desenvolva paixão pela música"
                Column {
                    Text(
                        text = stringResource(R.string.desenvolvendo_paixao),
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 8.dp)
                    )
                    Row {
                        Text(
                            text = stringResource(R.string.pela),
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                        Text(
                            text = stringResource(R.string.musica),
                            color = Color(0XFFF5A623),
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 8.dp, top = 8.dp)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.img_titulo),
                contentDescription = stringResource(R.string.jovem_tocando_violao),
            )
        }
    }
}

@Preview
@Composable
fun TitlePreview(){
    TitleMusic({} ,modifier = Modifier)
}