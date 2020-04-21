package com.earaujo.recyclerdiffutil.gui

import com.earaujo.recyclerdiffutil.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class GuiActivity : AppCompatActivity() {

    private val guiFilter =
        GuiAdapterMultiTypes<GuiModel, GuiViewHolderImpl<GuiModel>>(
            listOf(
                GuiModel.StringModel::class to { parent ->
                    GuiViewHolderImpl.StringViewHolder(
                        parent,
                        ::clickCallback
                    )
                },
                GuiModel.UserModel::class to { parent ->
                    GuiViewHolderImpl.UserViewHolder(
                        parent,
                        ::clickCallback
                    )
                }
            )
        )

    private var items = mutableListOf<GuiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        items.add(GuiModel.StringModel("Hoje"))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Rafael", 35))

        items.add(GuiModel.StringModel("Ontem"))
        items.add(GuiModel.UserModel("Gabriel", 21))
        items.add(GuiModel.UserModel("Gabriel", 21))
        items.add(GuiModel.UserModel("Gabriel", 21))
        items.add(GuiModel.UserModel("Gabriel", 21))
        items.add(GuiModel.UserModel("Leonardo", 18))

        items.add(GuiModel.StringModel("Amanhã"))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("José", 47))

        setupRecyclerViewGenericListAdapter()
    }

    private fun setupRecyclerViewGenericListAdapter() {
        selectRecyclerView.apply {
            val maxSpan = 2
            layoutManager = GridLayoutManager(this@GuiActivity, maxSpan).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int) =
                        if (items[position] is GuiModel.StringModel) 2 else 1
                }
            }
        }
        selectRecyclerView.adapter = guiFilter
        guiFilter.submitList(items)
        selectRecyclerView.setAnimationDuration(500)
        shuffleButton.setOnClickListener {
            items = items.shuffled().toMutableList()
            guiFilter.submitList(items.toList())
        }
    }

    private fun clickCallback(position: Int) {
        items.removeAt(position)
        guiFilter.submitList(items.toList())
    }

}
