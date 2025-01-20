package com.kaito.sogni.screens.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kaito.sogni.screens.AppVM
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_paws

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepSheet(
    onDone: () -> Unit
) {
    var isShowed = true
    if (isShowed) {
        val appVM = koinViewModel<AppVM>()
        val config by appVM.config.collectAsState()
        val sheetState = rememberModalBottomSheetState()
        var sliderPos by remember {
            mutableIntStateOf(config.step)
        }

        ModalBottomSheet(
            onDismissRequest = {
                isShowed = false
                appVM.setConfig(config.copy(step = sliderPos))
                onDone.invoke()
            },
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_paws),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "Steps"
                )

                Spacer(modifier = Modifier.weight(1F))

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "$sliderPos"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Slider(
                    value = sliderPos.toFloat(),
                    onValueChange = {
                        sliderPos = it.toInt()
                    },
                    steps = 0,
                    valueRange = 0F..60F,
                    thumb = { state ->
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.Red, RoundedCornerShape(10.dp))
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}