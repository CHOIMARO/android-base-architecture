package com.company.android_base_architecture.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.company.android_base_architecture.navigation.Destination
import com.company.designsystem.icon.IconWrapper

@Composable
fun BottomBarNavigation(
    navController: NavHostController,
    destinations: List<Destination>,
    onItemClick: (Destination) -> Unit
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        destinations.forEach { destination ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.hasRoute(destination.route::class)
            } == true

            CreateNavigationBarItem(
                item = destination,
                selected = isSelected, // selected 상태를 여기서 계산해서 넘김
                onClick = {
                    onItemClick(destination)
                }
            )
        }
    }
}

@Composable
private fun RowScope.CreateNavigationBarItem(
    item: Destination,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        icon = {
            val icon = if (selected) {
                item.selectedIcon
            } else {
                item.unselectedIcon
            }
            when (icon) {
                is IconWrapper.ImageVectorIcon -> Icon(
                    imageVector = icon.imageVector,
                    contentDescription = "icon",
                )

                is IconWrapper.DrawableResourceIcon -> Icon(
                    painter = painterResource(id = icon.id),
                    contentDescription = "icon"
                )
            }
        },
        label = {
            Text(text = stringResource(id = item.iconTextId))
        },
        selected = selected,
        alwaysShowLabel = true,
        onClick = onClick
    )
}