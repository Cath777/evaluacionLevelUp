package com.example.levelupgamerapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val usuario: String,
    val gmail: String,
    val contrasena: String,
    val fechaNacimiento: String, // formato ISO_LOCAL_DATE: "YYYY-MM-DD"
    val descuentoduoc: Boolean,
    val reCodigo: String, // Código de referido de este usuario
    val referredBy: String?, // Código de referido de quien lo invitó
    val leveluppuntos: Int
)
