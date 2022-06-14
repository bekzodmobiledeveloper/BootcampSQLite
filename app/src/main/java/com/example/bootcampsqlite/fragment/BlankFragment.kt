package com.example.bootcampsqlite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.bootcampsqlite.BootCamp
import com.example.bootcampsqlite.R
import com.example.bootcampsqlite.databinding.FragmentBlankBinding
import com.example.bootcampsqlite.fragment.dialog.EditDialogFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class BlankFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentBlankBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        binding.backMv.setOnClickListener {
            findNavController().popBackStack()
        }
        val bundle = this.arguments
        val bootCamp: BootCamp = bundle?.getSerializable("boot") as BootCamp
        binding.textName.text = bootCamp.name
        binding.textMatn.text = bootCamp.text


        binding.imageButton.setOnClickListener {
            val b = bundleOf("boot" to bootCamp)
            val editDialogFragment =
                EditDialogFragment.newInstance("Dialog1", "Dialog2")
            editDialogFragment.setListener {

            }
            editDialogFragment.isCancelable = false
            editDialogFragment.arguments = b
            editDialogFragment.show(childFragmentManager, "Dialog1")
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}