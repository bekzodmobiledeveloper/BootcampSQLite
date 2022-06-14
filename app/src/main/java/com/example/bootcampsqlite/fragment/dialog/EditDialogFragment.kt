package com.example.bootcampsqlite.fragment.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.example.bootcampsqlite.databinding.FragmentEditDialogBinding
import com.example.bootcampsqlite.dp.MyDpHelper


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditDialogFragment : DialogFragment() {
    private var listener: ((String) -> Unit)? = null
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentEditDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    lateinit var myDpHelper: MyDpHelper
    var bolim = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditDialogBinding.inflate(inflater, container, false)
        val options = arrayOf("Asosiy", "Dunyo", "Ijtimoiy")
        binding.spinnerSp.adapter = ArrayAdapter<String>(
            binding.root.context,
            R.layout.item_spinner,R.id.spinner_sp,
            options
        )
        binding.spinnerSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                bolim = options[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        binding.closeTv.setOnClickListener { dismiss() }
        val bundle = this.arguments
        val bootCamp: BootCamp = bundle?.getSerializable("boot") as BootCamp
        binding.sarlavha.setText(bootCamp.name)
        binding.matnTv.setText(bootCamp.text)
        binding.spinnerSp.prompt = bootCamp.bolim
        var id = 0;
        when (bootCamp.bolim) {
            "Asosiy" -> id = 0;
            "Dunyo" -> id = 1;
            "Ijtimoiy" -> id = 2;
        }
        binding.spinnerSp.setSelection(id)



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
                val editBootCamp = BootCamp(bootCamp.id, name1, text1, bolim)
                myDpHelper.updateInformatsion(editBootCamp)
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
            EditDialogFragment().apply {
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

    fun setListener(block: (String) -> Unit) {
        listener = block
    }
}