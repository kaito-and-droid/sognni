package com.kaito.sogni.screens

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hasRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class Destiny(val title: String = "", val hasTopBar: Boolean, val hasBottomBar: Boolean) {

    @Serializable
    data object Splash: Destiny(hasTopBar = false, hasBottomBar = false)

    @Serializable
    data object Onboard: Destiny(hasTopBar = false, hasBottomBar = false)

    @Serializable
    data object Home: Destiny(title = "Discover", hasTopBar = true, hasBottomBar = true)
}

fun NavBackStackEntry?.getDestiny(): Destiny? {
    return this?.let {
        when {
            destination.hasRoute(Destiny.Splash::class) -> Destiny.Splash
            destination.hasRoute(Destiny.Onboard::class) -> Destiny.Onboard
            destination.hasRoute(Destiny.Home::class) -> Destiny.Home
            else -> null
        }
    }
}