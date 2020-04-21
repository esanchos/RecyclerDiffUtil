package com.earaujo.recyclerdiffutil.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.earaujo.recyclerdiffutil.R
import com.earaujo.recyclerdiffutil.data.FullData.fullData
import com.earaujo.recyclerdiffutil.feature.model.FullModel
import com.earaujo.recyclerdiffutil.feature.model.FullViewHolder
import com.earaujo.recyclerdiffutil.setAnimationDuration
import com.earaujo.recyclerdiffutil.util.FullAdapter
import kotlinx.android.synthetic.main.activity_main.*

class FullActivity : AppCompatActivity() {

    private val adapter =
        FullAdapter<FullModel, FullViewHolder<*>>(
            listOf(
                FullModel.Header::class to { parent ->
                    FullViewHolder.HeaderViewHolder(parent)
                },
                FullModel.Image::class to { parent ->
                    FullViewHolder.ImageViewHolder(parent, ::clickCallback)
                }
            )
        )

    private var items = fullData.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_activity)
        setup()
    }

    private fun setup() {
        selectRecyclerView.layoutManager = GridLayoutManager(this, HEADER_SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int) =
                    if (items[position] is FullModel.Header) HEADER_SPAN_COUNT else IMAGE_SPAN_COUNT
            }
        }
        selectRecyclerView.adapter = adapter
        adapter.submitList(items)
        selectRecyclerView.setAnimationDuration(500)

        shuffleButton.setOnClickListener {
            items = items.shuffled().toMutableList()
            adapter.submitList(items.toList())
        }
    }

    private fun clickCallback(position: Int) {
        items.removeAt(position)
        adapter.submitList(items.toList())
    }

    companion object {
        private const val IMAGE_SPAN_COUNT = 1
        private const val HEADER_SPAN_COUNT = 4
    }

}
