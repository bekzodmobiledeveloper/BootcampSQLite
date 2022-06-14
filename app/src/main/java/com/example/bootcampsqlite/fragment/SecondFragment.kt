package com.example.bootcampsqlite.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bootcampsqlite.R
import com.example.bootcampsqlite.adapter.ViewPagerAdapter
import com.example.bootcampsqlite.databinding.FragmentSecondBinding
import com.example.bootcampsqlite.fragment.dialog.AddFragment
import com.example.bootcampsqlite.fragment.viewpager.AsosiyFragment
import com.example.bootcampsqlite.fragment.viewpager.DunyoFragment
import com.example.bootcampsqlite.fragment.viewpager.IjtimoiyFragment


class SecondFragment : Fragment() {
//private val navArgs:SecondFragmentArgs by navArgs()
    lateinit var binding: FragmentSecondBinding
    private var   pages :ArrayList<Fragment> = arrayListOf(
        AsosiyFragment(),
        DunyoFragment(),
        IjtimoiyFragment()
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        setUpTabs()

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_item -> {
                    val addFragment =
                        AddFragment.newInstance("Dialog1", "Dialog2")
                  addFragment.isCancelable = false
                    addFragment.show(childFragmentManager, "Dialog1")
                    addFragment.setListener {
                        if (it.equals("Asosiy"))
                        (pages[0] as AsosiyFragment).updateList()
                        if (it.equals("Dunyo")){
                            (pages[1] as DunyoFragment).updateList()
                        }
                        if (it.equals("Ijtimoiy")){
                            (pages[2] as IjtimoiyFragment).updateList()
                        }
                    }
                }
            }
            true
        }
        return binding.root
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(pages[0],"Asosiy")
        adapter.addFragment(pages[1],"Dunyo")
        adapter.addFragment(pages[2],"Ijtimoiy")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
fun view(){
    //viewpager positsiya berish
   // val pos=navArgs.position
}



}