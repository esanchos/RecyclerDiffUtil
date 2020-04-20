package com.earaujo.recyclerdiffutil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.earaujo.recyclerdiffutil.gui.GuiAdapterMultiTypes
import com.earaujo.recyclerdiffutil.gui.GuiModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val filterAdapter = SimpleAdapter(::listOnClick)
    private val filterAdapterWithAnimations =
        SimpleAdapterWithAnimations(::listOnClickWithAnimations)
    private val filterAdapterDiffUtil = SimpleAdapterDiffUtil(::listOnClickDiffUtil)
    private val filterListAdapter = SimpleListAdapter(::listOnClickListAdapter)
    private val filterString = GenericListAdapter{
        GenericViewHolderImpl.StringViewHolder(it, ::listOnClickGenericListAdapter)
    }

    private val items = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setupSimple()
//        setupSimpleWithAnimations()
//        setupSimpleDiffUtil()
//        setupSimpleListAdapter()
        setupGenericListAdapter()
    }

//    SIMPLE

    private fun setupSimple() {
        setupRecyclerView()
        setupButton()
    }

    private fun setupRecyclerView() {
        selectRecyclerView.adapter = filterAdapter
    }

    private fun setupButton() {
        addButton.setOnClickListener {
            items.add((1..100).shuffled().first().toString())
            (selectRecyclerView.adapter as SimpleAdapter).submitList(items)
        }
    }

    private fun listOnClick(position: Int) {
        items.removeAt(position)
        (selectRecyclerView.adapter as SimpleAdapter).submitList(items)
    }

//    WITH ANIMATIONS

    private fun setupSimpleWithAnimations() {
        setupRecyclerViewWithAnimations()
        setupButtonWithAnimations()
    }

    private fun setupRecyclerViewWithAnimations() {
        selectRecyclerView.adapter = filterAdapterWithAnimations
        selectRecyclerView.setAnimationDuration(500)
    }

    private fun setupButtonWithAnimations() {
        addButton.setOnClickListener {
            items.add((1..100).shuffled().first().toString())
            (selectRecyclerView.adapter as SimpleAdapterWithAnimations).submitListAdd(
                items,
                items.size - 1
            )
        }
    }

    private fun listOnClickWithAnimations(position: Int) {
        items.removeAt(position)
        (selectRecyclerView.adapter as SimpleAdapterWithAnimations).submitListRemove(
            items,
            position
        )
    }

//    DIFF UTIL

    private fun setupSimpleDiffUtil() {
        setupRecyclerViewDiffUtil()
        setupButtonDiffUtil()
    }

    private fun setupRecyclerViewDiffUtil() {
        selectRecyclerView.adapter = filterAdapterDiffUtil
        selectRecyclerView.setAnimationDuration(500)
    }

    private fun setupButtonDiffUtil() {
        addButton.setOnClickListener {
            items.add((1..100).shuffled().first().toString())
            (selectRecyclerView.adapter as SimpleAdapterDiffUtil).submitList(items)
        }
    }

    private fun listOnClickDiffUtil(position: Int) {
        items.removeAt(position)
        (selectRecyclerView.adapter as SimpleAdapterDiffUtil).submitList(items)
    }

//    LIST ADAPTER

    private fun setupSimpleListAdapter() {
        setupRecyclerViewListAdapter()
        setupButtonListAdapter()
    }

    private fun setupRecyclerViewListAdapter() {
        selectRecyclerView.adapter = filterListAdapter
        selectRecyclerView.setAnimationDuration(500)
    }

    private fun setupButtonListAdapter() {
        addButton.setOnClickListener {
            items.add((1..100).shuffled().first().toString())
            (selectRecyclerView.adapter as SimpleListAdapter).submitList(items.toList())
        }
    }

    private fun listOnClickListAdapter(position: Int) {
        items.removeAt(position)
        (selectRecyclerView.adapter as SimpleListAdapter).submitList(items.toList())
    }

    //    GENERIC LIST ADAPTER

    private fun setupGenericListAdapter() {
        setupRecyclerViewGenericListAdapter()
        setupButtonGenericListAdapter()
    }

    private fun setupRecyclerViewGenericListAdapter() {
        selectRecyclerView.adapter = filterString
        selectRecyclerView.setAnimationDuration(500)
    }

    private fun setupButtonGenericListAdapter() {
        /*addButton.setOnClickListener {
            items.add((1..100).shuffled().first().toString())
            filterString.submitList(items.toList())
        }
        shuffleButton.setOnClickListener {
            filterString.submitList(items.shuffled().toList())
        }*/
    }

    private fun listOnClickGenericListAdapter(position: Int) {
        items.removeAt(position)
        //filterString.submitList(items.toList())
    }

}
