package com.buildertrend.factfinder.list

import com.buildertrend.factfinder.FabConfiguration
import com.buildertrend.factfinder.R
import com.buildertrend.factfinder.Screen

object FactListScreen : Screen {

    override fun getFragment() = FactListFragment.newInstance()

    override fun getFabConfiguration() = FabConfiguration(R.drawable.ic_add, {})

    override fun getToolbarButtonLayoutId() = R.layout.view_select_fact_button
}
