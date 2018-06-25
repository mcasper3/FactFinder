package com.buildertrend.factfinder.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface FactDao {

    @Query("SELECT * FROM facts")
    fun getAll(): Flowable<List<Fact>>

    @Insert
    fun insert(fact: Fact): Long

    @Delete
    fun delete(fact: Fact)
}
