package com.romanvoytyuk.composition.domain.enteties

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level : Parcelable {
    TEST, EASY, NORMAL, HARD
}