package com.romanvoytyuk.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.romanvoytyuk.composition.R
import com.romanvoytyuk.composition.domain.enteties.GameResult

@BindingAdapter("requiredAnswers")
fun setRequiredAnswers(tv: TextView, count: Int) {
    tv.text = String.format(
        tv.context.getString(
            R.string.required_score,
            count.toString()
        )
    )
}

@BindingAdapter("requiredPercents")
fun setRequiredPercents(tv: TextView, percents: Int) {
    tv.text = String.format(
        tv.context.getString(
            R.string.required_percentage,
            percents.toString()
        )
    )
}

@BindingAdapter("emoji")
fun setEmoji(iv: ImageView, status: Boolean) {
    val image = if (status) R.drawable.ic_smile else R.drawable.ic_sad
    iv.setImageResource(image)
}

@BindingAdapter("percents")
fun setPercents(tv: TextView, gameResult: GameResult) {
    tv.text = String.format(
        tv.context.getString(
            R.string.score_percentage,
            calculatePercentageOfRightAnswers(gameResult).toString()
        )
    )

}

private fun calculatePercentageOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions != 0) {
        (countOfRightAnswers / countOfQuestions.toDouble() * 100).toInt()
    } else {
        0
    }
}

@BindingAdapter("score")
fun setScore(tv: TextView, gameResult: GameResult) {
    tv.text = String.format(
        tv.context.getString(
            R.string.right_answers,
            gameResult.countOfRightAnswers.toString(),
            gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
    )
}