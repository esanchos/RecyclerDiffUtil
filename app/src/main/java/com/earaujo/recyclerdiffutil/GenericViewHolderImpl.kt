package com.earaujo.recyclerdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_filter.view.*

sealed class GenericViewHolderImpl<M>(itemView: View) :
    GenericListAdapter.GenericViewHolder<M>(itemView) {

    class StringViewHolder(
        parent: ViewGroup,
        private val callback: (Int) -> Unit
    ) : GenericViewHolderImpl<String>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_filter, parent, false)
    ) {
        override fun bind(model: String) {
            itemView.filterTextView.text = model
            itemView.setOnClickListener { callback(layoutPosition)  }
        }
    }

}
