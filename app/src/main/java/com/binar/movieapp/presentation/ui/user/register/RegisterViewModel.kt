package com.binar.movieapp.presentation.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.binar.movieapp.data.local.preference.UserPreferences
import com.binar.movieapp.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    fun registerUser(user: UserPreferences) {
        viewModelScope.launch {
            repository.setUser(user)
        }
    }

    fun getUser(): LiveData<UserPreferences> {
        return repository.getUser().asLiveData()
    }
}