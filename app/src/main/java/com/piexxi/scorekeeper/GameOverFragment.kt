package com.piexxi.scorekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.piexxi.scorekeeper.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private  lateinit var binding : FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentGameOverBinding.inflate(inflater)
        getData()
        listeners()
        return binding.root
    }

    private fun getData() {
       val args = GameOverFragmentArgs.fromBundle(requireArguments())
        binding.tvTeamOneFinalScoreGof.text = "${args.team1Name} : ${args.team1Score}"
        binding.tvTeamTwoFinalScoreGof.text = "${args.team2Name} : ${args.team2Score}"

        if(args.team1Score > args.team2Score)
        {
            binding.tvWinnerTeamGof.text = "${args.team1Name} Won !!!"
        }
        else if(args.team1Score == args.team2Score)
        {
            binding.tvWinnerTeamGof.text = "Match Tied..!!"
        }else
        {
            binding.tvWinnerTeamGof.text =   "${args.team2Name} Won !!!"
        }

    }

    private fun listeners() {
        binding.btNewGameGof.setOnClickListener{nextFragment()}
    }

    private fun nextFragment() {
        findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToTitleFragment())
    }
}