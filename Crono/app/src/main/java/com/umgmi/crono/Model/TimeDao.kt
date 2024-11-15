package com.umgmi.crono.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TimeDao {

    @Insert
    suspend fun insert(timeEntry: TimeEntry)

    @Update
    suspend fun update(timeEntry: TimeEntry)

    @Delete
    suspend fun delete(timeEntry: TimeEntry)

    @Query("SELECT * FROM time_entries")
    fun getAllTimeEntries(): LiveData<List<TimeEntry>>
}
