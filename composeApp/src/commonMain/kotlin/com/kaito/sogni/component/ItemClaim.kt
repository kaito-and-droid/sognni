package com.kaito.sogni.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.HorizontalDivider
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
import sogni.composeapp.generated.resources.ic_gift
import sogni.composeapp.generated.resources.ic_star

data class ClaimInfo(
    val event: String,
    val nextClaim: String,
    val amount: Float,
    val status: String
)

@Composable
fun ItemClaim(info: ClaimInfo) {
    Column {
        Row {
            Icon(
                painter = painterResource(Res.drawable.ic_gift),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = info.event,
                    fontSize = 14.sp
                )

                Text(
                    text = info.nextClaim,
                    color = Color.Green
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.padding(start = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${info.amount}"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(Res.drawable.ic_star),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1F))

            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = Color.Green
            )

            Text(
                text = info.status,
                color = Color.Green
            )
        }

        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}