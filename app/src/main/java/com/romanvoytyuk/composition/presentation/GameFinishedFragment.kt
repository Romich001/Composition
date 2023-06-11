package com.romanvoytyuk.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.romanvoytyuk.composition.R
import com.romanvoytyuk.composition.databinding.FragmentGameFinishedBinding
import com.romanvoytyuk.composition.domain.enteties.GameResult


class GameFinishedFragment : Fragment() {


    private lateinit var gameResult: GameResult
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinished == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()
    }

    private fun parsArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

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
            tvRequiredAnswer.text = String.format(getString(
                R.string.required_score,
                gameResult.gameSettings.minCountOfRightAnswers.toString())
            )
            tvRequiredPercentage.text = String.format(getString(
                R.string.required_percentage,
                gameResult.gameSettings.minPercentOfRightAnswers.toString())
            )
            tvScoreAnswer.text = String.format(getString(
                R.string.right_answers,
                gameResult.countOfRightAnswers.toString(),
                gameResult.gameSettings.minCountOfRightAnswers.toString())
            )
            tvScorePercentage.text = String.format(getString(
                R.string.score_percentage,
                calculatePercentageOfRightAnswers().toString())
            )
        }


    }

    private fun calculatePercentageOfRightAnswers() = with(gameResult) {
        if (countOfQuestions != 0) {
            countOfRightAnswers / countOfQuestions.toDouble() * 100
        } else {
            0
        }
    }



    private fun getEmojiId() = if (gameResult.winner) R.drawable.ic_smile else R.drawable.ic_sad

    private fun setNavigation() {
        binding.buttonRetry.setOnClickListener {
            retry()
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retry()
                }

            })
    }

    private fun retry() {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val KEY_GAME_RESULT = "game result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }

}
