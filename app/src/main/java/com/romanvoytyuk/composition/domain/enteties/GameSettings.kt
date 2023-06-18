package com.romanvoytyuk.composition.domain.enteties

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val timeInSeconds: Int,

    ) : Parcelable