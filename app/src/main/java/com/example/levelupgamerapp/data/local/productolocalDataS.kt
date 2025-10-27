package com.example.levelupgamerapp.data.local

import com.example.levelupgamerapp.data.local.dao.ProductoDao
import com.example.levelupgamerapp.data.local.entity.ProductoEntity

class ProductoLocalDataSource(private val productoDao: ProductoDao) {
    //obtener todos los datos
    suspend fun getAllProductos(): List<ProductoEntity>{
        return productoDao.getAllProductos()
    }
    //adjuntar varios productos
    suspend fun insertProductos(productos: List<ProductoEntity>){
        productoDao.insertAll(productos)
    }
    //adjuntar solo un producto
    suspend fun insertProducto(producto: ProductoEntity){
        productoDao.insert(producto)
    }
    //eliminar un producto
    suspend fun deleteProducto(producto: ProductoEntity){
        productoDao.delete(producto)
    }
    //eliminar todos los productos
    suspend fun deleteAll(){
        productoDao.deleteAll()
    }
}