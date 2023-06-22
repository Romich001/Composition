package com.romanvoytyuk.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.romanvoytyuk.composition.R
import com.romanvoytyuk.composition.domain.enteties.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

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

@BindingAdapter("numToString")
fun setNumTOString(tv: TextView, num: Int) {
    tv.text = num.toString()
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(tv: TextView, state: Boolean) {
    tv.setTextColor(getColorByState(tv.context, state))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, state: Boolean) {
    progressBar.progressTintList =
        ColorStateList.valueOf(getColorByState(progressBar.context, state))
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(tv: TextView, clickListener: OnOptionClickListener) {
    tv.setOnClickListener {
        clickListener.onOptionClick(tv.text.toString().toInt())
    }
}

private fun getColorByState(context: Context, state: Boolean): Int {
    val colorResId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}
