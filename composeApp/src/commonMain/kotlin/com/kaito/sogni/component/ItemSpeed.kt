package com.kaito.sogni.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ItemSpeed(
    isSelected: Boolean,
    icons: List<DrawableResource>,
    title: String,
    desc: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
            .background(
                if (isSelected) Color(0xFF181818) else Color.Transparent,
                if (isSelected) RoundedCornerShape(8.dp) else RectangleShape
            )
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            icons.forEach {
                Icon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(24.dp),
                    painter = painterResource(it),
                    contentDescription = null,
                    tint = if (isSelected) Color.Green else LocalContentColor.current
                )
            }

            Text(
                text = title,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = desc,
            fontSize = 12.sp
        )
    }
}