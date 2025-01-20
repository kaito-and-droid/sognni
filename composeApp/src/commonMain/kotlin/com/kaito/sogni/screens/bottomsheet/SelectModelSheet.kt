package com.kaito.sogni.screens.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
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
import com.kaito.sogni.component.ItemImage
import com.kaito.sogni.component.ItemModel
import com.kaito.sogni.data.model.Model
import com.kaito.sogni.screens.AppVM
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectModelSheet(
    onDone: () -> Unit
) {
    var isShowed = true
    if (isShowed) {
        val sheetState = rememberModalBottomSheetState(true)
        val appVM = koinViewModel<AppVM>()
        val config by appVM.config.collectAsState()
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
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Sogni.XL",
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.weight(1F))

                Text(
                    text = "Docs"
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "YouTube"
                )
                Text(
                    text = "Discord"
                )
            }

            val images = listOf(
                "https://cdn.pixabay.com/photo/2024/02/29/11/50/ai-generated-8604085_1280.jpg",
                "https://cdn.pixabay.com/photo/2022/12/05/21/24/awe-7637733_1280.jpg",
                "https://cdn.pixabay.com/photo/2022/09/13/11/29/girl-7451711_1280.jpg",
                "https://cdn.pixabay.com/photo/2021/01/12/15/46/winter-5911787_1280.jpg",
                "https://cdn.pixabay.com/photo/2024/02/13/07/05/ai-generated-8570323_1280.jpg",
                "https://cdn.pixabay.com/photo/2023/09/25/03/27/ai-generated-8274116_1280.png"
            )
            LazyRow(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                items(images.size) {
                    ItemImage(images[it])
                }
            }

            Text(
                modifier = Modifier
                    .padding(16.dp),
                text = "Sogni.XL is an outstanding all-around model capable of producing incredible results in a wide range of styles, including art, photography, film, comics, cartoons, digital arts, fantasy, and more.",
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Select a Model Version:"
                )

                Spacer(modifier = Modifier.weight(1F))

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val models = listOf(
                "Sogni.XL a2",
                "Sogni.XL a2 [8bit]",
                "Sogni.XL a2 896x896 [8bit]"
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(Color.Gray, RoundedCornerShape(8.dp))
            ) {
                items(models.size) {
                    ItemModel(
                        isSelected = models[it] == config.model.name,
                        model = models[it],
                        format = "1.024px"
                    ) {
                        appVM.setConfig(config.copy(model = Model(name = models[it])))
                        onDone.invoke()
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onClick = {
                }
            ) {
                Text(
                    text = "View All Models"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}