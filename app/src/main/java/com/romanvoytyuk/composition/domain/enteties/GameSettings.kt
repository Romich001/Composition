package com.romanvoytyuk.composition.domain.enteties

import java.io.Serializable

data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val timeInSeconds: Int,

) : Serializable
