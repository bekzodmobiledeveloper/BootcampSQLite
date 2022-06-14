package com.example.bootcampsqlite.fragment

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.bootcampsqlite.R
import com.example.bootcampsqlite.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        startTimeCounter(binding.root.context)
        findNavController().popBackStack()
        return binding.root
    }

    override fun onPause() {
        super.onPause()
        findNavController().navigate(R.id.secondFragment)
    }

    private fun startTimeCounter(view: Context) {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                findNavController().navigate(R.id.secondFragment)
            }
        }.start()
    }


}