package com.earaujo.recyclerdiffutil.feature.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.earaujo.recyclerdiffutil.R
import com.earaujo.recyclerdiffutil.util.FullAdapter
import com.earaujo.recyclerdiffutil.util.loadImage
import kotlinx.android.synthetic.main.full_item_list_header.view.*
import kotlinx.android.synthetic.main.full_item_list_image.view.*

sealed class FullViewHolder<M : FullModel>(itemView: View) :
    FullAdapter.AdapterViewHolder<FullModel>(itemView) {

    class HeaderViewHolder(
        parent: ViewGroup
    ) : FullViewHolder<FullModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.full_item_list_header, parent, false)
    ) {
        override fun bind(model: FullModel) {
            (model as? FullModel.Header)?.let {
                itemView.headerTextView.text = model.title
            }
        }
    }

    class ImageViewHolder(
        parent: ViewGroup,
        private val callback: (Int) -> Unit
    ) : FullViewHolder<FullModel>(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.full_item_list_image, parent, false)
    ) {
        override fun bind(model: FullModel) {
            (model as? FullModel.Image)?.let {
                itemView.titleTextView.text = model.name
                itemView.mainImageView.loadImage(itemView.context, model.imageUrl)
                itemView.setOnClickListener { callback(layoutPosition) }
            }
        }
    }

}
