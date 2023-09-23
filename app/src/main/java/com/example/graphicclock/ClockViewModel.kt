package com.example.graphicclock

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import java.time.LocalTime
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ClockViewModel : ViewModel() {

    private val _isHoursLeft = MutableLiveData<Int>()
    var isHoursLeft: LiveData<Int> = _isHoursLeft

    private val _isHoursRight = MutableLiveData<Int>()
    var isHoursRight: LiveData<Int> = _isHoursRight

    private val _isMinLeft = MutableLiveData<Int>()
    var isMinLeft: LiveData<Int> = _isMinLeft

    private val _isMinRight = MutableLiveData<Int>()
    var isMinRight: LiveData<Int> = _isMinRight

    private val _isSecLeft = MutableLiveData<Int>()
    var isSecLeft: LiveData<Int> = _isSecLeft

    private val _isSecRight = MutableLiveData<Int>()
    var isSecRight: LiveData<Int> = _isSecRight

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTime() {
        viewModelScope.launch {
            while (true) {
                val current = LocalTime.now()

                _isHoursLeft.value = current.hour / 10
                _isHoursRight.value = current.hour % 10

                _isMinLeft.value = current.minute / 10
                _isMinRight.value = current.minute % 10

                _isSecLeft.value = current.second / 10
                _isSecRight.value = current.second % 10

                delay(1000)
            }

        }


    }

}