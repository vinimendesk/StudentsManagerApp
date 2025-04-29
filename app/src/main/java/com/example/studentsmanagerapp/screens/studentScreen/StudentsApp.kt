package com.example.studentsmanagerapp.screens.studentScreen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentsmanagerapp.components.StudentBottomNavigation
import com.example.studentsmanagerapp.data.NavigationItemContentList
import com.example.studentsmanagerapp.navigation.AppNavigation
import com.example.studentsmanagerapp.ui.theme.StudentsManagerAppTheme

@Composable
fun StudentApp(
    viewModel: StudentsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    // Identifica qual a tela atual.
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(navBackStackEntry) {
        viewModel.onDestinationChanged(navBackStackEntry?.destination?.route)
    }
    // Coleta o estado da tela atual.
    val currentScreen by viewModel.currentScreen.collectAsState()

    Scaffold(
        bottomBar = {
            StudentBottomNavigation(
                onTabPressed = { navController.navigate(it) },
                currentScreen = currentScreen,
                navigationItemContentList = NavigationItemContentList.getNavigationContentList(),
                modifier = Modifier
                    .height(105.dp)
            )
        }
    ) { paddingValues ->

        AppNavigation(navController, modifier = Modifier.padding(paddingValues))

    }

}

@Preview
@Composable
fun StudentAppPreview() {
    StudentsManagerAppTheme {
        StudentApp()
    }
}