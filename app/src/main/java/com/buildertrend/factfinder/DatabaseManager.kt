package com.buildertrend.factfinder

import android.arch.persistence.room.Room
import android.content.Context
import com.buildertrend.factfinder.database.Fact
import com.buildertrend.factfinder.database.FactFinderDatabase
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DatabaseManager(private val context: Context) {

    private val database = Room.databaseBuilder(context, FactFinderDatabase::class.java, "factfinder_db")
        .build()
    private val factDao = database.factDao()

    fun getAllFacts() = factDao.getAll()

    fun insertFact(fact: String) {
        Single
            .create<Long>({ emitter ->
                try {
                    emitter.onSuccess(factDao.insert(Fact(information = fact)))
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            })
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteFact(fact: Fact) {
        Single
            .create<Long> { emitter ->
                factDao.delete(fact)

                emitter.onSuccess(1)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
