package com.buildertrend.factfinder.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(
    entities = [
        Fact::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FactFinderDatabase : RoomDatabase() {

    abstract fun factDao(): FactDao
}
