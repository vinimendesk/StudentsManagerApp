package com.example.studentsmanagerapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.studentsmanagerapp.R
import com.example.studentsmanagerapp.model.NavigationItemContent
import com.example.studentsmanagerapp.model.ScreenType

@Composable
fun StudentBottomNavigation(
    onTabPressed: (String) -> Unit,
    currentScreen: ScreenType,
    navigationItemContentList: List<NavigationItemContent>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0XFFF5A623)
    ) {
        for (navItem in navigationItemContentList) {
            NavigationBarItem(
                selected = currentScreen == navItem.screenType,
                onClick = { onTabPressed(navItem.screenType.screen) },
                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = if (currentScreen == navItem.screenType) Color.White else Color.Transparent,
                                shape = CircleShape
                            )
                            .border(
                                width = 4.dp,
                                color = Color.Black,
                                shape = CircleShape
                            )
                            .size(48.dp)
                    ) {
                        Icon(
                            imageVector = navItem.icon,
                            tint = Color.Black,
                            contentDescription = stringResource(R.string.aperte_aqui_para_gerenciar_os_alunos),
                            modifier = Modifier.align(Alignment.Center)
                        )                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = Color(0XFFE0E0E0),
                    unselectedIconColor = Color(0XFFE0E0E0)
                )
            )
        }
    }
}