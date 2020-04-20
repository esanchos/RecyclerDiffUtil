package com.earaujo.recyclerdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earaujo.recyclerdiffutil.gui.GuiModel
import kotlinx.android.synthetic.main.item_list_filter.view.filterTextView
import kotlinx.android.synthetic.main.item_list_user.view.*

sealed class GenericViewHolderImpl<M>(itemView: View) :
    GenericListAdapter.GenericViewHolder<M>(itemView) {

    class StringViewHolder(
        parent: ViewGroup,
        private val callback: (Int) -> Unit
    ) : GenericViewHolderImpl<GuiModel.StringModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_filter, parent, false)
    ) {
        override fun bind(model: GuiModel.StringModel) {
            itemView.filterTextView.text = model.name
            itemView.setOnClickListener { callback(layoutPosition)  }
        }
    }

    class UserViewHolder(
        parent: ViewGroup
    ) : GenericViewHolderImpl<GuiModel.UserModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_user, parent, false)
    ) {
        override fun bind(model: GuiModel.UserModel) {
            itemView.filterTextView.text = model.name
            itemView.ageTextView.text = model.age.toString()
        }
    }

}
