package com.romanvoytyuk.composition.domain.usecases

import com.romanvoytyuk.composition.domain.enteties.Question
import com.romanvoytyuk.composition.domain.repository.GameRepository

class GetQuestionUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(maxSumValue: Int): Question{
        return repository.generateQuestion(
            maxSumValue,
            COUNT_OF_OPTIONS
        )
    }
    companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}