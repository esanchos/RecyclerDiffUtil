package com.earaujo.recyclerdiffutil.gui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earaujo.recyclerdiffutil.R
import kotlinx.android.synthetic.main.item_list_filter.view.filterTextView
import kotlinx.android.synthetic.main.item_list_user.view.*

sealed class GuiViewHolderImpl<M : GuiModel>(itemView: View) :
    GuiAdapter.GenericViewHolder<GuiModel>(itemView) {

    class StringViewHolder(
        parent: ViewGroup,
        private val callback: (Int) -> Unit
    ) : GuiViewHolderImpl<GuiModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_filter, parent, false)
    ) {
        override fun bind(model: GuiModel) {
            (model as? GuiModel.StringModel)?.let {
                itemView.filterTextView.text = model.name
                itemView.setOnClickListener { callback(layoutPosition) }
            }
        }
    }

    class UserViewHolder(
        parent: ViewGroup,
        private val callback: (Int) -> Unit
    ) : GuiViewHolderImpl<GuiModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_user, parent, false)
    ) {
        override fun bind(model: GuiModel) {
            (model as? GuiModel.UserModel)?.let {
                itemView.filterTextView.text = model.name
                itemView.ageTextView.text = model.age.toString()
                itemView.setOnClickListener { callback(layoutPosition) }
            }
        }
    }

}
