package com.kaito.sogni.screens.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_onboard_1
import sogni.composeapp.generated.resources.ic_onboard_2
import sogni.composeapp.generated.resources.ic_onboard_3
import sogni.composeapp.generated.resources.next
import sogni.composeapp.generated.resources.onboard_desc_1
import sogni.composeapp.generated.resources.onboard_desc_2
import sogni.composeapp.generated.resources.onboard_desc_3
import sogni.composeapp.generated.resources.onboard_title_1
import sogni.composeapp.generated.resources.onboard_title_2
import sogni.composeapp.generated.resources.onboard_title_3
import sogni.composeapp.generated.resources.start

@Composable
fun OnboardScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val pagerState = rememberPagerState(pageCount = { 3 })
        HorizontalPager(
            state = pagerState,
            pageContent = {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val content = when(it) {
                        0 -> Triple(Res.drawable.ic_onboard_1, Res.string.onboard_title_1, Res.string.onboard_desc_1)
                        1 -> Triple(Res.drawable.ic_onboard_2, Res.string.onboard_title_2, Res.string.onboard_desc_2)
                        else -> Triple(Res.drawable.ic_onboard_3, Res.string.onboard_title_3, Res.string.onboard_desc_3)
                    }
                    Image(
                        painter = painterResource(content.first),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = stringResource(content.second),
                        fontSize = 32.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(content.third),
                        fontSize = 16.sp,
                        color = Color(0x3CEBEBF5),
                        textAlign = TextAlign.Center
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .size(12.dp)
                        .background(
                            color = if (index == pagerState.currentPage) Color(0xFF4870FF) else Color(0xFF21283F),
                            shape = RoundedCornerShape(12.dp)
                        )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1F))

        val scope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF21283F)),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    onNavigate.invoke()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        ) {
            Text(
                text = if (pagerState.currentPage == pagerState.pageCount - 1) stringResource(Res.string.start) else stringResource(Res.string.next),
                color = Color.White
            )
        }
    }
}