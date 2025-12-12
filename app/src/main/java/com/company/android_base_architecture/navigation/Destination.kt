package com.company.android_base_architecture.navigation

import com.company.designsystem.icon.AppIcons
import com.company.designsystem.icon.IconWrapper
import com.company.example.navigation.ExampleRoute
import com.company.example.R as ExampleR

enum class Destination(
    val route: Any,
    val selectedIcon: IconWrapper,
    val unselectedIcon: IconWrapper,
    val iconTextId: Int,
) {
    Example(
        route = ExampleRoute,
        selectedIcon = IconWrapper.ImageVectorIcon(AppIcons.SearchFilled),
        unselectedIcon = IconWrapper.ImageVectorIcon(AppIcons.Search),
        iconTextId = ExampleR.string.example,
    ),
}