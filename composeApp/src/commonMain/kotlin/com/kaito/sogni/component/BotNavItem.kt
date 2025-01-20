package com.kaito.sogni.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BotNavItem(
    title: String,
    icon: DrawableResource,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelected: () -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                if (!isSelected) {
                    onSelected.invoke()
                }
            }
            .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp))
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(icon),
            contentDescription = null,
            colorFilter = if (isSelected) ColorFilter.tint(color = Color(0xFF4870FF)) else null
        )

        Text(
            text = title,
            fontSize = 12.sp,
            color = if (isSelected) Color(0xFF4870FF) else Color(0x3CEBEBF5)
        )
    }
}