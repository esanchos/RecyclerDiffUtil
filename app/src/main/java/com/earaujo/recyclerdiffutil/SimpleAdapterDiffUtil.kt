package com.earaujo.recyclerdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_filter.view.*

class SimpleAdapterDiffUtil(private val callback: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilterViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_filter, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as FilterViewHolder).bind()

    override fun getItemCount() = differ.currentList.size

    fun submitList(items: List<String>) {
        differ.submitList(items.toList())
    }

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            val items = differ.currentList
            itemView.filterTextView.text = items[layoutPosition]
            itemView.setOnClickListener {
                callback(layoutPosition)
            }
        }
    }

}