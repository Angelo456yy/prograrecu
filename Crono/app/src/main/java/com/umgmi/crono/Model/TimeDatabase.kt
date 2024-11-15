package com.umgmi.crono.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TimeEntry::class], version = 1, exportSchema = false)
abstract class TimedDatabase : RoomDatabase() {
    abstract fun timeDao(): TimeDao
}
