package com.kaito.sogni.screens.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaito.sogni.screens.AppVM
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_gift
import sogni.composeapp.generated.resources.ic_star

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SparkSheet(
    onDone: () -> Unit
) {
    var isShow = true
    if (isShow) {
        val appVM = koinViewModel<AppVM>()
        val config by appVM.config.collectAsState()
        val sheetState = rememberModalBottomSheetState()
        ModalBottomSheet(
            onDismissRequest = {
                isShow = false
                onDone.invoke()
            },
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Your Sparks"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Rounded.Info,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null,
                    tint = Color.Green
                )

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "${config.spark.amount}",
                    color = Color.Green,
                    fontSize = 18.sp
                )

                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null,
                    tint = Color.Green
                )
            }

            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 32.dp),
                painter = painterResource(Res.drawable.ic_gift),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                text = "Your next generation will cost:",
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = "${config.spark.cost}",
                    color = Color.Green,
                    fontSize = 14.sp
                )

                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(Res.drawable.ic_star),
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}