package com.example.studentsmanagerapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studentsmanagerapp.R

@Composable
fun AddButtonStudent(addStudent: () -> Unit, modifier: Modifier) {
    FloatingActionButton(
        onClick = addStudent,
        modifier = Modifier
            .padding(16.dp)
            .size(50.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(Color(0xFF50E3C2)),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Black, RoundedCornerShape(8.dp))
                .padding(3.dp)
        ) {
            Icon(
                imageVector = (Icons.Default.Add),
                contentDescription = stringResource(R.string.adicionar_alunosFloatButton),
                tint = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun AddButtonStudentPreview() {
    AddButtonStudent({}, modifier = Modifier)
}