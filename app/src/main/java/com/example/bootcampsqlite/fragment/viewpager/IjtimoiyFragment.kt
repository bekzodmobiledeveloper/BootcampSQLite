package com.example.bootcampsqlite.fragment.viewpager

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.bootcampsqlite.BootCamp
import com.example.bootcampsqlite.R
import com.example.bootcampsqlite.adapter.RecycAdapter
import com.example.bootcampsqlite.databinding.FragmentAsosiyBinding
import com.example.bootcampsqlite.dp.MyDpHelper
import com.example.bootcampsqlite.fragment.dialog.EditDialogFragment


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class IjtimoiyFragment : Fragment(), RecycAdapter.OnMyClickListener {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentAsosiyBinding
    lateinit var myDpHelper: MyDpHelper
    lateinit var list: ArrayList<BootCamp>
    lateinit var recycAdapter: RecycAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAsosiyBinding.inflate(inflater, container, false)
        myDpHelper = MyDpHelper(requireContext())
        list = myDpHelper.getAllInformation("Ijtimoiy")
        recycAdapter = RecycAdapter(list, this)
        binding.rv.adapter = recycAdapter


        return binding.root
    }

    fun updateList() {
        list = myDpHelper.getAllInformation("Ijtimoiy")
        recycAdapter.list = list
        recycAdapter.notifyDataSetChanged()
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AsosiyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onMyClick(position: Int, bootCamp: BootCamp) {
        val bundle = bundleOf("boot" to bootCamp)
        findNavController().navigate(R.id.blankFragment, bundle)
    }

    override fun onEditDelete(position: Int, bootCamp: BootCamp, view: View) {
        val bundle = bundleOf("boot" to bootCamp)
        val popupMenu = PopupMenu(requireContext(), view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.item_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.edit_item -> {
                    val editDialogFragment =
                        EditDialogFragment.newInstance("Dialog1", "Dialog2")
                    editDialogFragment.setListener {
                        updateList()
                    }
                    editDialogFragment.isCancelable = false
                    editDialogFragment.arguments = bundle
                    editDialogFragment.show(childFragmentManager, "Dialog1")
                    //  findNavController().navigate(R.id.editDialogFragment,bundle)
                }
                R.id.delete_item -> {
                    val alertDialog = AlertDialog.Builder(binding.root.context)
                    alertDialog.setCancelable(false)
                    alertDialog.setTitle("Xabarni o'chirmoqchimisiz?")
                    //alertDialog.setMessage()
                    alertDialog.setPositiveButton(
                        "O'chirish",
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                myDpHelper.deleteInformation(bootCamp)
                                list.remove(bootCamp)
                                recycAdapter.notifyItemRemoved(position)
                                recycAdapter.notifyItemRangeChanged(position, list.size)
                            }
                        })
                    alertDialog.setNegativeButton(
                        "Bekor qilish",
                        object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {

                            }
                        })
                    alertDialog.show()
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }
}