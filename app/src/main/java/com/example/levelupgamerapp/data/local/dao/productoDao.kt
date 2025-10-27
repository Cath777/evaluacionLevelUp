package com.example.levelupgamerapp.data.local.dao

import androidx.room.*
import com.example.levelupgamerapp.data.local.entity.ProductoEntity

@Dao
interface ProductoDao{
    @Query("SELECT * FROM productos")
    suspend fun getAllProductos(): List<ProductoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<ProductoEntity>)

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(producto: ProductoEntity)

    @Delete
    suspend fun delete(producto: ProductoEntity)

   @Query("DELETE FROM productos")
   suspend fun deleteAll()
}