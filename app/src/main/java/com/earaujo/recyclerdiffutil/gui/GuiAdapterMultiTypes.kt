package com.earaujo.recyclerdiffutil.gui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.reflect.KClass

class GuiAdapterMultiTypes<M: GuiAdapterMultiTypes.UiModel, VH: GuiViewHolderImpl<GuiModel>>(
    private val delegates: List<Pair<KClass<out M>, (ViewGroup) -> VH>>
    ): RecyclerView.Adapter<VH>() {

    private var items = listOf<GuiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegates[viewType].second(parent)

    override fun getItemViewType(position: Int) = items[position]::class.let {
            itemType -> delegates.indexOfFirst { it.first == itemType }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun submitList(items: List<GuiModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface UiModel {
    }

    abstract class GenericViewHolder<M>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(model: M)
    }

}