package com.earaujo.recyclerdiffutil.feature

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.earaujo.recyclerdiffutil.R
import com.earaujo.recyclerdiffutil.data.FullData
import com.earaujo.recyclerdiffutil.feature.model.FullModel
import com.earaujo.recyclerdiffutil.feature.model.FullViewHolder
import com.earaujo.recyclerdiffutil.setAnimationDuration
import com.earaujo.recyclerdiffutil.util.FullAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

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

    val fullData = FullData().fullData
    private var items: MutableList<FullModel> = fullData.toMutableList()
    private var sortByDate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_activity)
        setup()
    }

    private fun refreshListByDate() {
        var lastDate = 0L
        var headerCounter = 0
        items.clear()
        items.add(FullModel.Header("sampleid", "By Date"))
        fullData.sortedBy {
            it.timeStamp()
        }.forEach {
            if (lastDate != it.timeStamp()) {
                items.add(FullModel.Header(headerCounter.toString(), it.fileDate))
                headerCounter++
                lastDate = it.timeStamp()
            }
            items.add(it)
        }
    }

    private fun refreshBySize() {
        items.clear()
        items.add(FullModel.Header("sampleid", "By Size"))
        var lastSize = ""
        var headerCounter = 0
        fullData.sortedBy {
            it.fileSize
        }.forEach {
            if (lastSize != it.fileSection()) {
                items.add(FullModel.Header(headerCounter.toString(), it.fileSection()))
                headerCounter++
                lastSize = it.fileSection()
            }
            items.add(it)
        }
    }

    private fun FullModel.Image.fileSection(): String {
        return when (this.fileSize) {
            in 0..100000 -> "Até 100000"
            in 100001..1000000 -> "Até 1000000"
            else -> "Muito Grande"
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun FullModel.Image.timeStamp() =
        SimpleDateFormat("dd-MM-yyyy").parse(this.fileDate)?.time ?: 0

    private fun setup() {
        selectRecyclerView.layoutManager = GridLayoutManager(this, HEADER_SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when {
                        position >= items.size -> 0
                        items[position] is FullModel.Header -> HEADER_SPAN_COUNT
                        else -> IMAGE_SPAN_COUNT
                    }
                }

            }
        }
        println(fullData[0].id)
        println(fullData[0].id)
        selectRecyclerView.adapter = adapter
        refreshListByDate()
        adapter.submitList(items.toList())
//        selectRecyclerView.setAnimationDuration(200)

        shuffleButton.setOnClickListener {
            sortByDate = !sortByDate
            if (sortByDate) {
                refreshListByDate()
            } else {
                refreshBySize()
            }
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
