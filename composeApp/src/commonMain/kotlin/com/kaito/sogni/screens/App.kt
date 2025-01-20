package com.kaito.sogni

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kaito.sogni.component.MenuContent
import com.kaito.sogni.component.MenuItem
import com.kaito.sogni.data.model.ModelStatus
import com.kaito.sogni.data.model.Processing
import com.kaito.sogni.screens.AppVM
import com.kaito.sogni.screens.Command
import com.kaito.sogni.screens.Destiny
import com.kaito.sogni.screens.bottomsheet.BottomSheetManger
import com.kaito.sogni.screens.getDestiny
import com.kaito.sogni.screens.home.HomeScreen
import com.kaito.sogni.screens.onboard.OnboardScreen
import com.kaito.sogni.screens.splash.SplashScreen
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import sogni.composeapp.generated.resources.Res
import sogni.composeapp.generated.resources.ic_device
import sogni.composeapp.generated.resources.ic_electron
import sogni.composeapp.generated.resources.ic_gift
import sogni.composeapp.generated.resources.ic_guide
import sogni.composeapp.generated.resources.ic_model
import sogni.composeapp.generated.resources.ic_more
import sogni.composeapp.generated.resources.ic_paws
import sogni.composeapp.generated.resources.ic_prompt
import sogni.composeapp.generated.resources.ic_star
import sogni.composeapp.generated.resources.ic_style
import sogni.composeapp.generated.resources.ic_thunder

@Composable
fun App() {
    Napier.base(DebugAntilog())
    val controller: NavHostController = rememberNavController()
    val entry by controller.currentBackStackEntryAsState()
    val destiny = entry?.getDestiny()
    val appVM = koinViewModel<AppVM>()

    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        val wndStatus = WindowInsets.statusBars
        val wndBot = WindowInsets.navigationBars

        val config by appVM.config.collectAsState()
        Scaffold(
            topBar = {
                if (destiny?.hasTopBar == true) {
                    Column {
                        Spacer(
                            modifier = Modifier
                                .height(wndStatus.asPaddingValues().calculateTopPadding())
                        )
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(56.dp)
                                .background(Color(0xFF181818), RoundedCornerShape(8.dp))
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        appVM.sendCommand(Command.Gift)
                                    },
                                painter = painterResource(Res.drawable.ic_gift),
                                contentDescription = null
                            )

                            VerticalDivider(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .clickable {
                                        appVM.sendCommand(Command.Spark)
                                    }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    painter = painterResource(Res.drawable.ic_star),
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    text = "${config.spark.cost}"
                                )
                            }

                            VerticalDivider(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .clickable {
                                        appVM.sendCommand(Command.Processing)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (config.processing in listOf(Processing.Fast, Processing.Relaxed)) {
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp),
                                        painter = painterResource(Res.drawable.ic_electron),
                                        contentDescription = null
                                    )

                                    if (config.processing == Processing.Fast) {
                                        Icon(
                                            modifier = Modifier
                                                .size(24.dp),
                                            painter = painterResource(Res.drawable.ic_thunder),
                                            contentDescription = null
                                        )
                                    }
                                } else {
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp),
                                        painter = painterResource(Res.drawable.ic_device),
                                        contentDescription = null
                                    )
                                }
                            }

                            VerticalDivider(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier
                                    .clickable {
                                        appVM.checkStatus()
                                    }
                                    .fillMaxHeight()
                                    .border(1.dp, Color.Blue, RoundedCornerShape(8.dp))
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Black)
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (config.status in listOf(ModelStatus.Connected, ModelStatus.Imagining)) {
                                    Icon(
                                        painter = painterResource(Res.drawable.ic_electron),
                                        contentDescription = null,
                                        tint = Color.Green
                                    )
                                }

                                val text = when (config.status) {
                                    is ModelStatus.Disconnect -> "Connect"
                                    is ModelStatus.Connected -> "Imagine"
                                    is ModelStatus.Imagining -> "Generating..."
                                }
                                Text(
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    text = text,
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                if (destiny?.hasBottomBar == true) {
                    val menus = listOf(
                        MenuContent(icon = Res.drawable.ic_prompt, title = "Prompt", command = Command.Prompt),
                        MenuContent(icon = Res.drawable.ic_style, title = "Style", command = Command.Style),
                        MenuContent(icon = Res.drawable.ic_paws, title = "Steps", command = Command.Step),
                        MenuContent(icon = Res.drawable.ic_guide, title = "Guidance", command = Command.Guidance),
                        MenuContent(icon = Res.drawable.ic_model, title = "Model", command = Command.Model),
                        MenuContent(icon = Res.drawable.ic_more, title = "More", command = Command.More)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF181818))
                            .padding(
                                top = 16.dp,
                                bottom = wndBot.asPaddingValues().calculateBottomPadding()
                            ),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        menus.forEach {
                            MenuItem(it) { command ->
                                appVM.sendCommand(command)
                            }
                        }
                    }
                }
            }
        ) {
            NavHost(
                modifier = Modifier.padding(it),
                navController = controller,
                startDestination = Destiny.Splash,
            ) {
                composable<Destiny.Splash> {
                    SplashScreen { isShowed ->
                        if (isShowed) {
                            controller.navigate(Destiny.Home)
                        } else {
                            controller.navigate(Destiny.Onboard)
                        }
                    }
                }

                composable<Destiny.Onboard> {
                    OnboardScreen {
                        controller.navigate(Destiny.Home)
                    }
                }

                composable<Destiny.Home> {
                    HomeScreen()
                }
            }

            val command by appVM.commands.collectAsState()
            BottomSheetManger(command) {
                appVM.sendCommand(Command.Free)
            }
        }
    }
}