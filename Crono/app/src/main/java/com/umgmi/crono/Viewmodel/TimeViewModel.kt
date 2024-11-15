package com.umgmi.crono.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umgmi.crono.Model.TimeDao
import com.umgmi.crono.Model.TimeEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val timeDao: TimeDao
) : ViewModel() {

    private val _timeEntries = timeDao.getAllTimeEntries()
    val timeEntries: LiveData<List<TimeEntry>> = _timeEntries

    fun addTimeEntry(timeEntry: TimeEntry) {
        viewModelScope.launch {
            timeDao.insert(timeEntry)
        }
    }

    fun updateTimeEntry(timeEntry: TimeEntry) {
        viewModelScope.launch {
            timeDao.update(timeEntry)
        }
    }

    fun deleteTimeEntry(timeEntry: TimeEntry) {
        viewModelScope.launch {
            timeDao.delete(timeEntry)
        }
    }
}
