package com.kaito.sogni.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaito.sogni.data.model.Config
import com.kaito.sogni.data.model.DataState
import com.kaito.sogni.data.model.GenerativeImageRequest
import com.kaito.sogni.data.model.GenerativeImageResponse
import com.kaito.sogni.data.model.ModelStatus
import com.kaito.sogni.data.model.Reward
import com.kaito.sogni.data.model.SogniExp
import com.kaito.sogni.data.model.Spark
import com.kaito.sogni.data.repository.IImageRepo
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppVM(
    private val repo: IImageRepo
): ViewModel() {

    private val _config = MutableStateFlow(Config())
    val config: StateFlow<Config>
        get() = _config.asStateFlow()
    private val _commands = MutableStateFlow<Command>(Command.Free)
    val commands: StateFlow<Command>
        get() = _commands.asStateFlow()

    private val _images: MutableStateFlow<DataState<GenerativeImageResponse>> = MutableStateFlow(DataState.Init)
    val images: StateFlow<DataState<GenerativeImageResponse>>
        get() = _images.asStateFlow()

    init {
        loadConfig()
    }

    private fun loadConfig() = viewModelScope.launch {
        _config.value = Config(
            spark = Spark(1000F, 0.5F),
            rewards = listOf(Reward.DailyBoost(200F, "", "Claimed"))
        )
    }

    fun sendCommand(command: Command) = viewModelScope.launch {
        _commands.value = command
    }

    fun setConfig(config: Config) = viewModelScope.launch {
        _config.value = config
    }

    fun checkStatus() = viewModelScope.launch {
        when (_config.value.status) {
            ModelStatus.Disconnect -> {
                delay(1_000)
                _config.value = _config.value.copy(status = ModelStatus.Connected)
            }
            ModelStatus.Connected -> {
                _config.value = _config.value.copy(status = ModelStatus.Imagining)
                delay(3_000)
                _config.value = _config.value.copy(status = ModelStatus.Connected)
            }
            else -> {
            }
        }
    }

    fun generateImage(prompt: String) = viewModelScope.launch(Dispatchers.IO) {
        val request = GenerativeImageRequest(
            providers = "openai",
            text = prompt,
            resolution = "512x512"
        )
        _config.value = _config.value.copy(status = ModelStatus.Imagining)
        _images.value = DataState.Loading
        repo.genImage(request)
            .fold(
                onSuccess = {
                    Napier.d("Success $it")
                    _config.value = _config.value.copy(status = ModelStatus.Connected)
                    _images.value = DataState.Success(it)
                },
                onFailure = {
                    Napier.d("Failure: ${it.message}")
                    _config.value = _config.value.copy(status = ModelStatus.Connected)
                    if (it is SogniExp) {
                        _images.value = DataState.Failure(it)
                    } else {
                        // Handle unnknown exp
                    }
                }
            )
    }
}