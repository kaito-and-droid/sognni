package com.kaito.sogni.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kaito.sogni.data.model.DataState
import com.kaito.sogni.screens.AppVM
import io.github.aakira.napier.Napier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val appVM = koinViewModel<AppVM>()
    val image by appVM.images.collectAsState()
    Napier.d("App state: $image")

    Column(
        modifier = modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = image
        ) {
            when (it) {
                is DataState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.Blue
                        )
                    }
                }
                is DataState.Success -> {
                    AsyncImage(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .height(360.dp)
                            .fillMaxWidth(),
                        model = it.data.url,
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                is DataState.Failure -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Failed to load image"
                        )
                    }
                }
                else -> {

                }
            }
        }
    }
}