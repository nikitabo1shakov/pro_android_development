package com.nikitabolshakov.proandroiddevelopment.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitabolshakov.proandroiddevelopment.R
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.utils.convertMeaningsToString
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*

class MainActivityAdapter(
    private var onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<MainActivityAdapter.RecyclerItemViewHolder>() {

    private var data: List<SkyengDataModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<SkyengDataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: SkyengDataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = data.text
                itemView.description_textview_recycler_item.text =
                    convertMeaningsToString(data.meanings!!)
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: SkyengDataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: SkyengDataModel)
    }
}