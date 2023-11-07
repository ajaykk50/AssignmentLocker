package com.test.machine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.machine.R
import com.test.machine.pojo.GroupedData


class RowAdapter(
    var screenHeight: Double,
    var totalSum: Int,
    val position: Int,
    var groupedDataList: GroupedData
) :
    RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // You can reference the child views here if needed.
        val textView: TextView? = itemView.findViewById(R.id.textItem)
        val imageView: ImageView? = itemView.findViewById(R.id.imageItem)
        val llContainer: LinearLayout? = itemView.findViewById(R.id.llContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var sizeper: Double =
            (100 / totalSum.toDouble()) * groupedDataList.items.get(position).machineLocker.size

        val itemHeightPercentage: Double =
            (sizeper) / 100
        val itemHeight = (screenHeight * itemHeightPercentage)

        if (position == 0) {
            holder.llContainer?.setBackgroundResource(R.drawable.background)
        } else if (position == groupedDataList.items.size - 1) {
            holder.llContainer?.setBackgroundResource(R.drawable.background_bottom)
        }else{
            holder.llContainer?.setBackgroundResource(R.drawable.background)
        }


        val layoutParams = holder.textView?.layoutParams
        layoutParams?.height = itemHeight.toInt()
        holder.textView?.layoutParams = layoutParams

        val layoutParamsImage = holder.imageView?.layoutParams
        layoutParamsImage?.height = itemHeight.toInt()
        holder.imageView?.layoutParams = layoutParamsImage

        if (groupedDataList.items.get(position).machineLocker.size == 5) {
            holder.imageView?.setImageResource(R.drawable.ic_locker)
            holder.imageView?.visibility = View.VISIBLE
            holder.textView?.visibility = View.GONE
        } else {
            holder.imageView?.visibility = View.GONE
            holder.textView?.visibility = View.VISIBLE
            holder.textView?.text =
                groupedDataList.items.get(position).machineLocker.lockerNo.toString()

            if(groupedDataList.items.get(position).machineLocker.size == 6)
                holder.textView?.text = ""


        }


    }

    override fun getItemCount(): Int {
        return groupedDataList.items.size
    }
}

