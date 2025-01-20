package com.kaito.sogni.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_logo

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Boolean) -> Unit
) {
    val vm = koinViewModel<SplashVM>()

    val nextState by rememberUpdatedState(onNavigate)
    val animatable = remember {
        Animatable(0F)
    }
    LaunchedEffect(Unit) {
        animatable.animateTo(1F, tween(3_000))
        delay(500L)
        nextState.invoke(vm.isShowed())
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.graphicsLayer {
                scaleX = animatable.value
                scaleY = animatable.value
            },
            painter = painterResource(Res.drawable.ic_logo),
            contentDescription = null
        )
    }
}