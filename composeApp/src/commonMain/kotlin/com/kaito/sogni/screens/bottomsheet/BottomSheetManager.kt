package com.kaito.sogni.screens.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kaito.sogni.screens.AppVM
import com.kaito.sogni.screens.Command
import io.github.aakira.napier.Napier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BottomSheetManger(command: Command, onDone: () -> Unit) {
    when (command) {
        is Command.Gift -> {
            ClaimRewardSheet(onDone)
        }
        is Command.Spark -> {
            SparkSheet(onDone)
        }
        is Command.Processing -> {
            SpeedSheet(onDone)
        }
        is Command.Prompt -> {
            PromptSheet(onDone)
        }
        is Command.Step -> {
            StepSheet(onDone)
        }
        is Command.Style -> {
        }
        is Command.Model -> {
            SelectModelSheet(onDone)
        }
        is Command.Guidance -> {
            GuidanceSheet(onDone)
        }

        else -> {
        }
    }
}