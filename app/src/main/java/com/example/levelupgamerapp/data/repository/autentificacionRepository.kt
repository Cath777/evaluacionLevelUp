package com.example.levelupgamerapp.data.repository

import com.example.levelupgamerapp.data.local.dao.UsuarioDao
import com.example.levelupgamerapp.data.local.entity.UsuarioEntity
import com.example.levelupgamerapp.data.util.Resultado
import com.example.levelupgamerapp.model.Usuario

class AutentificacionRepository(private val usuarioDao: UsuarioDao) {

    suspend fun logear(gmail: String, contrasena: String): Resultado<Usuario> {
        val usuarioEntity = usuarioDao.logear(gmail, contrasena)
        return if (usuarioEntity != null) {
            val usuario = Usuario(
                id = usuarioEntity.id,
                usuario = usuarioEntity.usuario,
                gmail = usuarioEntity.gmail
            )
            Resultado.Logrado(usuario)
        } else {
            Resultado.Error("El correo electronico o contraseña se encuentran incorrectos.")
        }
    }

    suspend fun registrar(usuario: UsuarioEntity): Resultado<Boolean> {
        val existingUser = usuarioDao.obtenerUsuarioPorGmail(usuario.gmail)
        return if (existingUser != null) {
            Resultado.Error("Hay un usuario existente con este gmail.")
        } else {
            usuarioDao.registroUsuario(usuario)
            Resultado.Logrado(true)
        }
    }

    suspend fun getUserById(id: Int): UsuarioEntity? {
        return usuarioDao.getUserById(id)
    }

    suspend fun updateUser(usuario: UsuarioEntity) {
        usuarioDao.actualizarUsuario(usuario)
    }
}