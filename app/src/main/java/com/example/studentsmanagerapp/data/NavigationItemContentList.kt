package com.example.studentsmanagerapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MusicNote

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.model.NavigationItemContent
import com.example.studentsmanagerapp.model.ScreenType

object NavigationItemContentList {
    @Composable
    fun getNavigationContentList(): List<NavigationItemContent> {
        return listOf(
            NavigationItemContent(
                screenType = ScreenType.STUDENT,
                icon = Icons.Default.MusicNote,
                text = ScreenType.STUDENT.screen
            ),
            NavigationItemContent(
                screenType = ScreenType.SCHEDULE,
                icon = Icons.Default.DateRange,
                text = ScreenType.SCHEDULE.screen
            )
        )
    }
}