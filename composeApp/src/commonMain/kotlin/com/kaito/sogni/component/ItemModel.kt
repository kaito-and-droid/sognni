package com.kaito.sogni.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_electron

@Composable
fun ItemModel(
    isSelected: Boolean,
    model: String,
    format: String,
    onSelected: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onSelected.invoke()
            }
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.Green
            )
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Companion.Transparent, RoundedCornerShape(12.dp))
                    .border(1.dp, Color(0xFF181818), RoundedCornerShape(12.dp))
            )
        }

        Icon(
            modifier = Modifier
                .padding(start = 4.dp),
            painter = painterResource(Res.drawable.ic_electron),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = model,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.weight(1F))

        Text(
            text = format,
            fontSize = 14.sp
        )
    }
}