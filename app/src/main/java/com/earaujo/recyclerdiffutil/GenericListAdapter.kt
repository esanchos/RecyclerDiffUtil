package com.earaujo.recyclerdiffutil

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GenericListAdapter<M, VH : GenericViewHolderImpl<M>>(
    private val viewHolder: (ViewGroup) -> VH
) : ListAdapter<M, VH>(GeneralDiffCallback<M>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolder(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    abstract class GenericViewHolder<M>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(model: M)
    }

}

class GeneralDiffCallback<M> : DiffUtil.ItemCallback<M>() {
    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean = oldItem == newItem
}
