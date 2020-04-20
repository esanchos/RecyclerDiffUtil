package com.earaujo.recyclerdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_filter.view.*

class SimpleListAdapter(private val callback: (Int) -> Unit) :
    ListAdapter<String, SimpleListAdapter.SelectViewHolder>(object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder {
        return SelectViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_filter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class SelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(value: String) {
            itemView.filterTextView.text = value
            itemView.setOnClickListener { callback(layoutPosition) }
        }
    }

}
