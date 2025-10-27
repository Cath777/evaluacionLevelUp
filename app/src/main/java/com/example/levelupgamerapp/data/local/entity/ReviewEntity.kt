package com.example.levelupgamerapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reviews")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val userId: Int,
    val rating: Int, // 1 siento la nota mas baja / 5 siendo la nota mas alta de la review
    val comment: String?,
    val createdAt: String // Fecha y hora de la review
)
