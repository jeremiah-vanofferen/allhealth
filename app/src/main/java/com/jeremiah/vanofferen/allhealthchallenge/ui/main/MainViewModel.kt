package com.jeremiah.vanofferen.allhealthchallenge.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<String?>()
    internal val result : LiveData<String?> = _result

    private var selectedColor: String? = null
        set(value) {
            field = value
            selectedShape?.let { shape ->
                resetJob = reset
                _result.postValue("${field}_${shape}_result.png")
            } ?: _result.postValue(null)

        }
    private var selectedShape: String? = null
        set(value) {
            field = value
            selectedColor?.let { color ->
                resetJob = reset
                _result.postValue("${color}_${field}_result.png")
            } ?: _result.postValue(null)
        }

    private val reset: Job
        get() = viewModelScope.launch {
            delay(300000)
            selectedColor = null
            selectedShape = null
        }

    private var resetJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    internal fun onRedClicked() {
        selectedColor = "red"
    }

    internal fun onYellowClicked() {
        selectedColor = "yellow"
    }

    internal fun onBlueClicked() {
        selectedColor = "blue"
    }

    internal fun onGreenClicked() {
        selectedColor = "green"
    }

    internal fun onRectangleClicked() {
        selectedShape = "rectangle"
    }

    internal fun onOvalClicked() {
        selectedShape = "oval"
    }

    internal fun onTriangleClicked() {
        selectedShape = "triangle"
    }

    internal fun onStarClicked() {
        selectedShape = "star"
    }
}