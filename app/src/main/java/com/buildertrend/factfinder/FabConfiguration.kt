package com.buildertrend.factfinder

class FabConfiguration(
    val iconResId: Int,
    val clickHandler: (() -> Unit)?,
    val isVisible: Boolean = true
) {

    companion object {
        fun hidden() = FabConfiguration(iconResId = 0, clickHandler = null, isVisible = false)
    }
}
