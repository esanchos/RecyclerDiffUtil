package com.earaujo.recyclerdiffutil

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setAnimationDuration(duration: Long) {
    this.itemAnimator?.apply {
        moveDuration = duration
        addDuration = duration
        removeDuration = duration
        changeDuration = duration
    }
}