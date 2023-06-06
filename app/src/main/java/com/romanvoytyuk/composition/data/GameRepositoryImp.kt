package com.romanvoytyuk.composition.data

import com.romanvoytyuk.composition.domain.enteties.GameSettings
import com.romanvoytyuk.composition.domain.enteties.Level
import com.romanvoytyuk.composition.domain.enteties.Question
import com.romanvoytyuk.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImp : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val options = getOptions(rightAnswer, maxSumValue, countOfOptions)
        return Question(sum, visibleNumber, options.toList())
    }

    private fun getOptions(
        rightAnswer: Int,
        maxSumValue: Int,
        countOfOptions: Int
    ): List<Int> {
        val options = mutableSetOf<Int>()
        options.add(rightAnswer)
        val to = min(maxSumValue, rightAnswer + countOfOptions)
        val from = max(rightAnswer - countOfOptions, MIN_ANSWER_VALUE)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(from, to))
        }
        return options.toList()
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8
                )
            }

            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60

                )
            }

            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40

                )
            }

            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    90,
                    40

                )
            }
        }
    }
}