package com.example.levelupgamerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamerapp.data.local.entity.UsuarioEntity
import com.example.levelupgamerapp.data.repository.AutentificacionRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: AutentificacionRepository, private val userId: Int) : ViewModel() {

    private val _user = MutableLiveData<UsuarioEntity?>()
    val user: LiveData<UsuarioEntity?> = _user

    init {
        loadUserData()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _user.value = repository.getUserById(userId)
        }
    }

    fun updateUsername(newName: String) {
        viewModelScope.launch {
            val currentUser = _user.value
            if (currentUser != null) {
                val updatedUser = currentUser.copy(usuario = newName)
                repository.updateUser(updatedUser)
                _user.value = updatedUser
            }
        }
    }
}
