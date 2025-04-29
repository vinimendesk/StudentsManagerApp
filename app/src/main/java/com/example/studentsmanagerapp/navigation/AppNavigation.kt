package com.example.studentsmanagerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentsmanagerapp.model.ScreenType
import com.example.studentsmanagerapp.screens.studentScreen.ScheduleScreen
import com.example.studentsmanagerapp.screens.studentScreen.StudentsScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

//    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenType.STUDENT.screen
    ) {

        composable(ScreenType.STUDENT.screen) {
            StudentsScreen(
                navController = navController,
                modifier = modifier
            )
        }

        composable(ScreenType.SCHEDULE.screen) {
            ScheduleScreen(
                navController = navController,
                modifier = modifier
            )
        }

    }
}