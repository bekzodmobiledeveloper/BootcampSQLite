package com.example.bootcampsqlite.fragment.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.bootcampsqlite.BootCamp
import com.example.bootcampsqlite.R
import com.example.bootcampsqlite.databinding.FragmentAddBinding
import com.example.bootcampsqlite.dp.MyDpHelper


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddFragment : DialogFragment() {
    private var listener: ((String) -> Unit)? = null
    private var bolim = "Asosiy"
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    lateinit var myDpHelper: MyDpHelper
    lateinit var binding: FragmentAddBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.closeTv.setOnClickListener { dismiss() }

        myDpHelper = MyDpHelper(requireContext())
        // spinner dan foydalandim
        val options = arrayOf("Asosiy", "Dunyo", "Ijtimoiy")
        binding.spinnerSp.adapter = ArrayAdapter<String>(
            binding.root.context,
            R.layout.item_spinner,R.id.spinner_sp,
            options
        )

        binding.spinnerSp.prompt = "salom"
      //  binding.spinnerSp.isSelected=false
        binding.spinnerSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                bolim = options[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        // save qilish
        binding.saveTv.setOnClickListener {
            val name = binding.sarlavha.text.toString()
            val text = binding.matnTv.text.toString()
            val bolim = bolim
            val name1 = name.trim()
            val text1 = text.trim()

            if (name1.isEmpty() || text1.isEmpty() || bolim.isEmpty()) {
                Toast.makeText(
                    binding.root.context,
                    "Ma'lumotlar to'liq emas!!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                myDpHelper = MyDpHelper(requireContext())
                val bootCamp = BootCamp(name1, text1, bolim)
                myDpHelper.addInformation(bootCamp)
                listener?.invoke(bootCamp.bolim!!)
                Toast.makeText(binding.root.context, "Ma'lumotlar jo'natildi!!", Toast.LENGTH_SHORT)
                    .show()
                dismiss()
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }



    fun setListener(f: (String) -> Unit) {
        listener = f
    }
}