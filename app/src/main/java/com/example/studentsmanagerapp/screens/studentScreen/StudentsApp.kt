package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentsmanagerapp.ui.theme.StudentsManagerAppTheme

@Composable
fun StudentApp() {

    Scaffold(
        bottomBar ={ }
    ) { paddingValues ->
        StudentsScreen(modifier = Modifier.padding(paddingValues))
    }

}

@Preview
@Composable
fun StudentAppPreview() {
    StudentsManagerAppTheme {
        StudentApp()
    }
}