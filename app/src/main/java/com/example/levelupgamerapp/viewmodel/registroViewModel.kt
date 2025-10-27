package com.example.levelupgamerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.levelupgamerapp.data.local.dao.UsuarioDao
import com.example.levelupgamerapp.data.local.entity.UsuarioEntity
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.UUID

class registroViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun registro(
        usuario: String,
        gmail: String,
        contrasena: String,
        fechaNacimientoStr: String,
        referralCode: String?,
        onResultado: (success: Boolean, message: String) -> Unit
    ) {
        viewModelScope.launch {
            try {

                val fechaNacimiento = LocalDate.parse(fechaNacimientoStr, dateFormatter)
                val edad = Period.between(fechaNacimiento, LocalDate.now()).years
                if (edad < 18) {
                    onResultado(false, "Debes ser mayor de edad para poder registrarte")
                    return@launch
                }


                val descuentoduoc = gmail.lowercase().endsWith("@duoc.cl")


                val codigopersonal = "LVLUP-" + UUID.randomUUID().toString().substring(0, 8).uppercase()


                val nuevoUsuario = UsuarioEntity(
                    usuario = usuario,
                    gmail = gmail,
                    contrasena = contrasena,
                    fechaNacimiento = fechaNacimiento.toString(),
                    descuentoduoc = descuentoduoc,
                    reCodigo = codigopersonal,
                    referredBy = referralCode,
                    leveluppuntos = 0
                )

                usuarioDao.registroUsuario(nuevoUsuario)


                if (!referralCode.isNullOrBlank()) {
                    val referer = usuarioDao.getUsuarioPorRecodigo(referralCode)
                    if (referer != null) {
                        val updatedReferer = referer.copy(leveluppuntos = referer.leveluppuntos + 100)
                        usuarioDao.actualizarUsuario(updatedReferer)
                    }
                }

                onResultado(true, "Registro exitoso, ¡Bienvenido a Level Up Gamer!")
            } catch (e: Exception) {
                onResultado(false, e.message ?: "Error de registro: ${e.localizedMessage}")
            }
        }
    }
}