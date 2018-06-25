package com.buildertrend.factfinder

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class FactFinderApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}
