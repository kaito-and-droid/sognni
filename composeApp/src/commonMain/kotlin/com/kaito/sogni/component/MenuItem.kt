package com.kaito.sogni.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaito.sogni.screens.Command
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class MenuContent(
    val icon: DrawableResource,
    val title: String,
    val command: Command
)

@Composable
fun MenuItem(
    content: MenuContent,
    onMenuClick: (Command) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onMenuClick.invoke(content.command)
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(content.icon),
            contentDescription = null,
            tint = Color.White
        )

        Text(
            text = content.title,
            fontSize = 12.sp
        )
    }
}