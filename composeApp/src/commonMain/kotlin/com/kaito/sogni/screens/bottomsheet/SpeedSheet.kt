package com.kaito.sogni.screens.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaito.sogni.component.ItemSpeed
import com.kaito.sogni.data.model.Processing
import com.kaito.sogni.screens.AppVM
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_device
import sogni.composeapp.generated.resources.ic_electron
import sogni.composeapp.generated.resources.ic_processing
import sogni.composeapp.generated.resources.ic_thunder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeedSheet(onDone: () -> Unit) {
    var isShowed = true
    if (isShowed) {
        val appVM = koinViewModel<AppVM>()
        val config by appVM.config.collectAsState()

        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
        ModalBottomSheet(
            onDismissRequest = {
                isShowed = false
                onDone.invoke()
            },
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_processing),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "Processing:",
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(Color.Black, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ){
                item {
                    ItemSpeed(
                        config.processing == Processing.Fast,
                        listOf(Res.drawable.ic_electron, Res.drawable.ic_thunder),
                        "Fast Supernet",
                        "Supernet of premium workers for lightning-fast processing."
                    ) {
                        appVM.setConfig(config.copy(processing = Processing.Fast))
                        onDone.invoke()
                    }
                }
                item {
                    ItemSpeed(
                        config.processing == Processing.Relaxed,
                        listOf(Res.drawable.ic_electron),
                        "Relaxed Supernet",
                        "Community-driven supernet of workers. Get more images for your Sparks."
                    ) {
                        appVM.setConfig(config.copy(processing = Processing.Relaxed))
                        onDone.invoke()
                    }
                }
                item {
                    ItemSpeed(
                        config.processing == Processing.Device,
                        listOf(Res.drawable.ic_device),
                        "On-Device",
                        "On-device processing: no Wifi or signup required. Free and unlimited."
                    ) {
                        appVM.setConfig(config.copy(processing = Processing.Device))
                        onDone.invoke()
                    }
                }
            }

            val wnd = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            Spacer(modifier = Modifier.height(wnd + 32.dp))
        }
    }
}