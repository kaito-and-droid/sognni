package com.kaito.sogni.screens.bottomsheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaito.sogni.screens.AppVM
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_prompt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptSheet(onDone: () -> Unit) {
    var isShowed = true
    if (isShowed) {
        val sheetState = rememberModalBottomSheetState()
        val appVM = koinViewModel<AppVM>()
        ModalBottomSheet(
            onDismissRequest = {
                isShowed = false
                onDone.invoke()
            },
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_prompt),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = "Prompt"
                )

                Spacer(modifier = Modifier.weight(1F))

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            var prompt by remember {
                mutableStateOf("")
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = prompt,
                onValueChange = {
                    prompt = it
                },
                placeholder = {
                    Text(
                        text = "Describe your dream in detail..",
                        fontSize = 12.sp
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            prompt = ""
                        },
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color.Blue),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                onClick = {
                    appVM.generateImage(prompt)
                    onDone.invoke()
                }
            ) {
                Text(
                    text = "Imagine",
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Green
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}