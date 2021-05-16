package com.piexxi.scorekeeper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.piexxi.scorekeeper.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTitleBinding.inflate(inflater)
        listners()
        return binding.root
    }

    private fun listners() {
        binding.btStartrGameTf.setOnClickListener { nextFragment() }
    }

    private fun nextFragment() {
        val team1 = binding.etTeamOneTf.text.toString()
        val team2 = binding.etTeamTwoTf.text.toString()

        if (team1 == "") {
            binding.etTeamOneTf.setError("Please Enter Team Name..")
        } else if (team2 == "") {
            binding.etTeamTwoTf.setError("Please Enter Team Name..")
        } else {
            findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment(team1, team2))
        }
    }
}