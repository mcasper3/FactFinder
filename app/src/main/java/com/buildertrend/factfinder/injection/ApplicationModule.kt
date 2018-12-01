package com.buildertrend.factfinder.injection

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val appContext: Context) {

    @Provides
    fun provideAppContext() = appContext
}
