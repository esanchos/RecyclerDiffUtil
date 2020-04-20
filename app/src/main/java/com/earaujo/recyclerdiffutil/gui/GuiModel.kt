package com.earaujo.recyclerdiffutil.gui

sealed class GuiModel: GuiAdapterMultiTypes.UiModel {

    data class StringModel(val name: String) : GuiModel()

    data class UserModel(val name: String, val age: Int) : GuiModel()

}