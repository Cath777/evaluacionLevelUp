package com.example.levelupgamerapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ResenaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val productoId: Int,
    val usuarioId: Int,
    val calificacion: Int, // <- desde el 1(baja calificacion) hasta el 5(alta calificacion)
    val comentario: String?,
    val fecha: String
)
