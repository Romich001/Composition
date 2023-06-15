package com.romanvoytyuk.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.romanvoytyuk.composition.R
import com.romanvoytyuk.composition.databinding.FragmentGameFinishedBinding


class GameFinishedFragment : Fragment() {


    private val args by navArgs<GameFinishedFragmentArgs>()

    private val gameResult by lazy {

        args.gameResult
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinished == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigation()
        setInfo()
    }

    private fun setInfo() {
        with(binding) {
            emojiResult.setImageResource(getEmojiId())
            tvRequiredAnswer.text = String.format(
                getString(
                    R.string.required_score,
                    gameResult.gameSettings.minCountOfRightAnswers.toString()
                )
            )
            tvRequiredPercentage.text = String.format(
                getString(
                    R.string.required_percentage,
                    gameResult.gameSettings.minPercentOfRightAnswers.toString()
                )
            )
            tvScoreAnswer.text = String.format(
                getString(
                    R.string.right_answers,
                    gameResult.countOfRightAnswers.toString(),
                    gameResult.gameSettings.minCountOfRightAnswers.toString()
                )
            )
            tvScorePercentage.text = String.format(
                getString(
                    R.string.score_percentage,
                    calculatePercentageOfRightAnswers().toString()
                )
            )
        }


    }

    private fun calculatePercentageOfRightAnswers() = with(gameResult) {
        if (countOfQuestions != 0) {
            (countOfRightAnswers / countOfQuestions.toDouble() * 100).toInt()
        } else {
            0
        }
    }


    private fun getEmojiId() = if (gameResult.winner) R.drawable.ic_smile else R.drawable.ic_sad

    private fun setNavigation() {
        binding.buttonRetry.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
