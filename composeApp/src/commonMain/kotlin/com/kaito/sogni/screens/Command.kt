package com.kaito.sogni.screens

sealed interface Command {
    data object Prompt: Command
    data object Style: Command
    data object Step: Command
    data object Guidance: Command
    data object Model: Command
    data object More: Command

    data object Gift: Command

    data object Spark: Command

    data object Processing: Command

    data object Connect: Command

    data object Free: Command
}