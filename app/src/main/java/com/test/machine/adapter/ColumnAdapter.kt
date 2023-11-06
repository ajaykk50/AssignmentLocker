package com.test.machine.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.machine.R
import com.test.machine.pojo.GroupedData


class ColumnAdapter(
    var mainActivity: Context,
    var largestColumnSequence: Int?,
    var groupedDataList: List<GroupedData>?,
    var screenHeight: Double
) :
    RecyclerView.Adapter<ColumnAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // You can reference the child views here if needed.
//        val textView: TextView? = itemView.findViewById(R.id.textItem)
//        val imageView: ImageView? = itemView.findViewById(R.id.imageItem)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.rvColumn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_column, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        holder.recyclerView.layoutManager =
//            LinearLayoutManager(mainActivity, LinearLayoutManager.VERTICAL, false)
//        if (position == 0) {
//            holder.recyclerView.layoutManager = GridLayoutManager(mainActivity, 10)
//        } else if (position == 1) {
//            holder.recyclerView.layoutManager = GridLayoutManager(mainActivity, 14)
//        } else {
//            holder.recyclerView.layoutManager = GridLayoutManager(mainActivity, 3)
//        }

        var count: Int? = groupedDataList?.get(position)?.items?.size

        var totalSum = groupedDataList?.get(position)?.items?.sumBy { it.machineLocker.size }

//        holder.recyclerView.layoutManager =
//            GridLayoutManager(mainActivity, count!!, GridLayoutManager.VERTICAL, false)

        holder.recyclerView.layoutManager = LinearLayoutManager(mainActivity)
        val adapter = groupedDataList?.get(position)
            ?.let { RowAdapter(screenHeight, totalSum!!, position, it) }
        holder.recyclerView.adapter = adapter
    }

    override fun getItemCount(): Int {
        return largestColumnSequence!!
    }
}

