package com.buildertrend.factfinder

import android.app.Application
import com.buildertrend.factfinder.injection.ApplicationComponent
import com.buildertrend.factfinder.injection.ApplicationModule
import com.buildertrend.factfinder.injection.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary

class FactFinderApplication : Application() {

    lateinit var component: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
