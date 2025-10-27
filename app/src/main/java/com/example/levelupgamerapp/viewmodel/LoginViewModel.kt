package com.example.levelupgamerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamerapp.data.repository.AutentificacionRepository
import com.example.levelupgamerapp.data.util.Resultado
import com.example.levelupgamerapp.model.Usuario
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AutentificacionRepository) : ViewModel() {

    fun login(
        gmail: String,
        contrasena: String,
        onResultado: (Resultado<Usuario>) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = repository.logear(gmail, contrasena)
            onResultado(resultado)
        }
    }
}
