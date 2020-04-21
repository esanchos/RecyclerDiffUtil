package com.earaujo.recyclerdiffutil.feature.model

import com.earaujo.recyclerdiffutil.util.FullAdapter
import java.util.*

sealed class FullModel : FullAdapter.AdapterModel<String> {

    abstract override val id: String

    data class Header(
        override val id: String,
        val title: String
    ) : FullModel()

    data class Image(
        override val id: String = UUID.randomUUID().toString(),
        val imageUrl: String,
        val name: String,
        val fileSize: Long,
        val fileDate: String
    ) : FullModel()

}
