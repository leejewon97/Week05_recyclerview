package com.example.week05_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week05_recyclerview.databinding.ActivityMainBinding
import com.example.week05_recyclerview.databinding.RowBinding

class MyDataAdapter(val items:ArrayList<MyData>):RecyclerView.Adapter<MyDataAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(data: MyData)
    }
    var itemClickListener:OnItemClickListener?=null
    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.textView1.setOnClickListener {
                itemClickListener?.onItemClick(items[adapterPosition])
                if (binding.textView2.visibility == View.VISIBLE) {
                    binding.textView2.visibility = View.GONE
                } else if (binding.textView2.visibility == View.GONE) {
                    binding.textView2.visibility = View.VISIBLE
                }
            }
        }
    }

    fun moveItem(oldPos:Int, newPos:Int){
        val tmp = items[newPos]
        items[newPos] = items[oldPos]
        items[oldPos] = tmp
        notifyItemMoved(oldPos, newPos)
    }
    fun removeItem(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
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
        holder.binding.textView2.text = items[position].meaning
        holder.binding.textView2.visibility = View.GONE
    }
}