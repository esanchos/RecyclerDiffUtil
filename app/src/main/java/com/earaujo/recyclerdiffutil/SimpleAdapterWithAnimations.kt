package com.earaujo.recyclerdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_filter.view.*

class SimpleAdapterWithAnimations(private val callback: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilterViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_filter, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as FilterViewHolder).bind()

    override fun getItemCount() = items.size

    fun submitListAdd(items: List<String>, position: Int) {
        this.items = items
        notifyItemInserted(position)
    }

    fun submitListRemove(items: List<String>, position: Int) {
        this.items = items
        notifyItemRemoved(position)
    }

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.filterTextView.text = items[layoutPosition]
            itemView.setOnClickListener {
                callback(layoutPosition)
            }
        }
    }

}
