package com.rdt.sepatuku.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object DetailItem : Screen("home/{ItemId}") {
        fun createRoute(ItemId: Long) = "home/$ItemId"
    }
}
