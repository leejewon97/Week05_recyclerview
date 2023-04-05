package com.example.week05_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week05_recyclerview.databinding.RowBinding

class MyDataAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyDataAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(data: MyData, adapterPosition: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.textView1.setOnClickListener {
                itemClickListener?.onItemClick(items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textView1.text = items[position].word
        holder.binding.textView2.text = items[position].mean
        holder.binding.textView2.visibility = items[position].visibility
    }
}