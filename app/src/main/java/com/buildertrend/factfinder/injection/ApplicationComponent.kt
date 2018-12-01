package com.buildertrend.factfinder.injection

import com.buildertrend.factfinder.MainActivity
import com.buildertrend.factfinder.database.DatabaseModule
import com.buildertrend.factfinder.database.FactFinderDatabase
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun database(): FactFinderDatabase
}
