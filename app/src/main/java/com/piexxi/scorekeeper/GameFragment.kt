package com.piexxi.scorekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.piexxi.scorekeeper.databinding.FragmentGameBinding
import com.piexxi.scorekeeper.databinding.FragmentTitleBinding
import kotlin.math.round

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    var teamOneTotalShots = 0
    var teamOneMadeShots = 0

    var teamTwoTotalShots = 0
    var teamTwoMadeShots = 0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // return super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentGameBinding.inflate(inflater)
        getData()
        listners()
        binding.rbQ1.isChecked = true
        return binding.root
    }

    private fun getData() {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        binding.tvTeamOneNameGf.text = args.team1Name
        binding.tvTeamTwoNameGf.text = args.team2Name
    }

    private fun listners() {
        binding.btFthrowTeam1.setOnClickListener { teamOneSHoot(1) }
        binding.bt2ptTeam1.setOnClickListener { teamOneSHoot(2) }
        binding.bt3ptTeam1.setOnClickListener { teamOneSHoot(3) }
        binding.btMissedShotTeam1.setOnClickListener { teamOneSHoot(0) }

        binding.btFthrowTeam2.setOnClickListener { teamTwoSHoot(1) }
        binding.bt2ptTeam2.setOnClickListener { teamTwoSHoot(2) }
        binding.bt3ptTeam2.setOnClickListener { teamTwoSHoot(3) }
        binding.btMissedShotTeam2.setOnClickListener { teamTwoSHoot(0) }

        binding.btEndGame.setOnClickListener { nextFragment() }
    }

    private fun teamOneSHoot(pointValue: Int) {
        if(pointValue != 0)
        {
            teamOneMadeShots++

            val newScore = binding.tvTeam1ScoreGf.text.toString().toInt() + pointValue
            binding.tvTeam1ScoreGf.text = newScore.toString()

            when{
                binding.rbQ1.isChecked -> {
                    val  quarterScore = binding.tvT1Q1.text.toString().toInt() + pointValue
                    binding.tvT1Q1.text = quarterScore.toString()
                }

                binding.rbQ2.isChecked -> {
                    val  quarterScore = binding.tvT1Q2.text.toString().toInt()+ pointValue
                    binding.tvT1Q2.text = quarterScore.toString()
                }

                binding.rbQ3.isChecked -> {
                    val  quarterScore = binding.tvT1Q3.text.toString().toInt()+ pointValue
                    binding.tvT1Q3.text = quarterScore.toString()
                }

                binding.rbQ4.isChecked -> {
                    val  quarterScore = binding.tvT1Q4.text.toString().toInt()+ pointValue
                    binding.tvT1Q4.text = quarterScore.toString()
                }
            }
        }
        teamOneTotalShots++

        val shootingPercentage = round(teamOneMadeShots.toFloat()/teamOneTotalShots *10000) /100
        binding.tvPercentageTeam1.text = "$shootingPercentage %"

    }

    private fun teamTwoSHoot(pointValue: Int) {
        if(pointValue != 0)
        {
            teamTwoMadeShots++

            val newScore = binding.tvTeam2ScoreGf.text.toString().toInt() + pointValue
            binding.tvTeam2ScoreGf.text = newScore.toString()

            when{
                binding.rbQ1.isChecked -> {
                    val  quarterScore = binding.tvT2Q1.text.toString().toInt() + pointValue
                    binding.tvT2Q1.text = quarterScore.toString()
                }

                binding.rbQ2.isChecked -> {
                    val  quarterScore = binding.tvT2Q2.text.toString().toInt() + pointValue
                    binding.tvT2Q2.text = quarterScore.toString()
                }

                binding.rbQ3.isChecked -> {
                    val  quarterScore = binding.tvT2Q3.text.toString().toInt() + pointValue
                    binding.tvT2Q3.text = quarterScore.toString()
                }

                binding.rbQ4.isChecked -> {
                    val  quarterScore = binding.tvT2Q4.text.toString().toInt() + pointValue
                    binding.tvT2Q4.text = quarterScore.toString()
                }
            }
        }
        teamTwoTotalShots++

        val shootingPercentage = round(teamTwoMadeShots.toFloat()/teamTwoTotalShots *10000) /100
        binding.tvPercentageTeam2.text = "$shootingPercentage %"
    }

    private fun nextFragment() {
         val team1 = binding.tvTeamOneNameGf.text.toString()
          val team2 = binding.tvTeamTwoNameGf.text.toString()

        val team1TotalScore = binding.tvTeam1ScoreGf.text.toString()
        val team2TotalScore = binding.tvTeam2ScoreGf.text.toString()

          findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(team1,team1TotalScore,team2,team2TotalScore))

    }
}