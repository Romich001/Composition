package com.romanvoytyuk.composition.domain.repository

import com.romanvoytyuk.composition.domain.enteties.GameSettings
import com.romanvoytyuk.composition.domain.enteties.Level
import com.romanvoytyuk.composition.domain.enteties.Question

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ) : Question

    fun getGameSettings(level: Level) : GameSettings
}