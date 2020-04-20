package com.earaujo.recyclerdiffutil.gui

import com.earaujo.recyclerdiffutil.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class GuiActivity : AppCompatActivity() {

    private val guiFilter =
        GuiAdapterMultiTypes<GuiModel, GuiViewHolderImpl<GuiModel>>(
            listOf(
                GuiModel.StringModel::class to { parent -> GuiViewHolderImpl.StringViewHolder(parent, ::clickCallback) },
                GuiModel.UserModel::class to { parent -> GuiViewHolderImpl.UserViewHolder(parent, ::clickCallback) }
            )
        )

    private val items = mutableListOf<GuiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        items.add(GuiModel.StringModel("Hoje"))
        items.add(GuiModel.UserModel("Eduardo", 38))
        items.add(GuiModel.UserModel("Rafael", 35))

        items.add(GuiModel.StringModel("Ontem"))
        items.add(GuiModel.UserModel("Gabriel", 21))
        items.add(GuiModel.UserModel("Leonardo", 18))

        items.add(GuiModel.StringModel("Amanhã"))
        items.add(GuiModel.UserModel("Joaquim", 61))
        items.add(GuiModel.UserModel("José", 47))

        setupRecyclerViewGenericListAdapter()
    }

    private fun setupRecyclerViewGenericListAdapter() {
        selectRecyclerView.adapter = guiFilter
        guiFilter.submitList(items)
        selectRecyclerView.setAnimationDuration(500)
    }

    private fun clickCallback(position: Int) {
        println("$position")
    }

}
