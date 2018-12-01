package com.buildertrend.factfinder.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(appContext: Context): FactFinderDatabase = Room.databaseBuilder(appContext, FactFinderDatabase::class.java, "factfinder_db")
        .build()
}
