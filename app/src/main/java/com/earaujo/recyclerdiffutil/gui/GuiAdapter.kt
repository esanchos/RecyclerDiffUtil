package com.earaujo.recyclerdiffutil.gui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GuiAdapter<M: GuiModel, VH: GuiViewHolderImpl<M>>(
    private val viewHolder: (ViewGroup) -> VH
): RecyclerView.Adapter<VH>() {

    private var items = listOf<M>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = viewHolder(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(items: List<M>) {
        this.items = items
        notifyDataSetChanged()
    }

    abstract class GenericViewHolder<M>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(model: M)
    }

}