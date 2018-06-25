package com.buildertrend.factfinder.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "facts")
data class Fact(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val information: String
)
