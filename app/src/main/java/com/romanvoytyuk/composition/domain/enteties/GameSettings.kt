package com.romanvoytyuk.composition.domain.enteties

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val timeInSeconds: Int,

)
