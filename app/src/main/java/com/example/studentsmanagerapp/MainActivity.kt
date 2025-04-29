package com.example.studentsmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentsmanagerapp.screens.studentScreen.StudentApp
import com.example.studentsmanagerapp.ui.theme.StudentsManagerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudentsManagerAppTheme {
                    StudentApp(
                        modifier = Modifier.padding()
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudentsManagerAppTheme {
        StudentApp(modifier = Modifier)
    }
}