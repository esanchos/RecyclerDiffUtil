package com.earaujo.recyclerdiffutil.feature.model

import com.earaujo.recyclerdiffutil.util.FullAdapter

sealed class FullModel : FullAdapter.AdapterModel<String> {

    abstract override val id: String

    data class Header(
        override val id: String,
        val title: String
    ) : FullModel()

    data class Image(
        override val id: String,
        val imageUrl: String,
        val name: String
    ) : FullModel()

}
