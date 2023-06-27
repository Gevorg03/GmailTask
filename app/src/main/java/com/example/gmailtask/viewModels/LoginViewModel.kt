package com.example.gmailtask.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private var _email = MutableStateFlow("")
    private var _error = MutableStateFlow(false)

    val email: StateFlow<String>
        get() = _email

    val error: StateFlow<Boolean>
        get() = _error

    fun login(email: String) = viewModelScope.launch {
        _email.value = email
    }

    fun error(error: Boolean) = viewModelScope.launch {
        _error.value = error
    }
}