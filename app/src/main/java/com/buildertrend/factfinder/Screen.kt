package com.buildertrend.factfinder

import android.support.v4.app.Fragment

interface Screen {

    fun getFragment(): Fragment

    fun getFabConfiguration(): FabConfiguration

    fun  getToolbarButtonLayoutId(): Int?
}
