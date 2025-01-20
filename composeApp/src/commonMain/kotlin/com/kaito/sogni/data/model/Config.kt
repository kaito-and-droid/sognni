package com.kaito.sogni.data.model

data class Config(
    val prompt: String = "",
    val seed: Long = 0L,
    val step: Int = 10,
    val scale: Float = 5F,
    val model: Model = Model(),
    val spark: Spark = Spark(1000F, 0.5F),
    val processing: Processing = Processing.Fast,
    val rewards: List<Reward> = emptyList(),
    val status: ModelStatus = ModelStatus.Disconnect
)

data class Model(
    val name: String = ""
)

data class Spark(
    val amount: Float,
    val cost: Float
)

sealed interface Processing {
    data object Fast: Processing
    data object Relaxed: Processing
    data object Device: Processing
}

sealed interface ModelStatus {
    data object Disconnect: ModelStatus
    data object Connected: ModelStatus
    data object Imagining: ModelStatus
}

sealed class Reward {
    data class DailyBoost(val amount: Float, val claimedTime: String, val status: String): Reward()
}