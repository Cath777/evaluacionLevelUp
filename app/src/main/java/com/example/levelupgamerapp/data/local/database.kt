package com.example.levelupgamerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.levelupgamerapp.data.local.dao.ProductoDao
import com.example.levelupgamerapp.data.local.dao.ReviewDao
import com.example.levelupgamerapp.data.local.dao.UsuarioDao
import com.example.levelupgamerapp.data.local.entity.CarroComprasEntity
import com.example.levelupgamerapp.data.local.entity.ReviewEntity
import com.example.levelupgamerapp.data.local.entity.UsuarioEntity
import com.example.levelupgamerapp.data.local.entity.ProductoEntity

@Database(
    entities = [UsuarioEntity::class, ProductoEntity::class, ReviewEntity::class, CarroComprasEntity::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun usuarioDao(): UsuarioDao
    abstract fun productoDao(): ProductoDao
    abstract fun reviewDao(): ReviewDao
}