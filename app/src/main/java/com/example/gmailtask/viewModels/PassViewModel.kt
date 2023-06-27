package com.example.gmailtask.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PassViewModel : ViewModel() {
    private var _pass = MutableStateFlow("")
    private var _isShowed = MutableStateFlow(false)

    val pass: StateFlow<String>
        get() = _pass

    val isShowed: StateFlow<Boolean>
        get() = _isShowed

    fun setPass(email: String) = viewModelScope.launch {
        _pass.value = email
    }

    fun setShowed(show: Boolean) {
        _isShowed.value = show
    }
}