package com.romanvoytyuk.composition.domain.usecases

import com.romanvoytyuk.composition.domain.enteties.GameSettings
import com.romanvoytyuk.composition.domain.enteties.Level
import com.romanvoytyuk.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level) : GameSettings {
        return repository.getGameSettings(level = level)
    }
}