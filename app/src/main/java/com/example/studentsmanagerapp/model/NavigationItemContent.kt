package com.example.studentsmanagerapp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val screenType: ScreenType,
    val icon: ImageVector,
    val text: String
)

enum class ScreenType(val screen: String) {
    STUDENT("Alunos"),
    SCHEDULE("Agenda"),
    FAVORITE("Favoritos")
}