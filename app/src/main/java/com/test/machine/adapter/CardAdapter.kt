package com.test.machine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.machine.home.HomeFragment.Companion.getViewModel
import com.test.machine.pojo.CardDetails
import com.test.machine.databinding.CardItemBinding


class CardAdapter(
    context: Context,
    dataLists: List<CardDetails?>?
) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private var dataList = dataLists

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        );
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList?.get(position))
    }

    override fun getItemCount(): Int {
        return if (dataList.isNullOrEmpty()) 0 else dataList!!.size
    }


    inner class ViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CardDetails?) {
            binding.data = item
            binding.viewModel = getViewModel()
            binding.executePendingBindings()
        }
    }
}
