package com.example.bootcampsqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampsqlite.BootCamp
import com.example.bootcampsqlite.databinding.ItemBootBinding

class RecycAdapter(var list: ArrayList<BootCamp>, var onMyClickListener: OnMyClickListener) :
    RecyclerView.Adapter<RecycAdapter.Vh>() {

    inner class Vh(var itemBootBinding: ItemBootBinding) :
        RecyclerView.ViewHolder(itemBootBinding.root) {
        fun onBind(bootCamp: BootCamp) {
            itemBootBinding.nameTv.text = bootCamp.name
            itemBootBinding.matnTv.text = bootCamp.text

            itemBootBinding.cardVi.setOnClickListener {
                onMyClickListener.onMyClick(adapterPosition, bootCamp)
            }
            itemBootBinding.nuqtaMv.setOnClickListener {
                onMyClickListener.onEditDelete(adapterPosition, bootCamp, itemBootBinding.nuqtaMv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBootBinding.inflate(LayoutInflater.from(parent.context), null, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun add(data: BootCamp) {
        list.add(data)
        notifyDataSetChanged()
    }

    interface OnMyClickListener {
        fun onMyClick(position: Int, bootCamp: BootCamp)
        fun onEditDelete(position: Int, bootCamp: BootCamp, view: View)
    }
}