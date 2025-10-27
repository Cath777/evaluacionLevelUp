package com.example.levelupgamerapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.levelupgamerapp.model.CarroCompras
import com.example.levelupgamerapp.model.Producto

class CarroViewModel : ViewModel() {

    var carroCompras by mutableStateOf(CarroCompras())
        private set

    var totalItems by mutableStateOf(0)
        private set

    fun agregarProducto(producto: Producto) {
        carroCompras.agregarProducto(producto)
        carroCompras = CarroCompras(carroCompras.items.toMutableMap())
        actualizarConteo()
    }

    fun removerProducto(producto: Producto) {
        carroCompras.removerProducto(producto)
        carroCompras = CarroCompras(carroCompras.items.toMutableMap())
        actualizarConteo()
    }

    fun removerItemPorCompleto(producto: Producto) {
        carroCompras.removerItemPorCompleto(producto)
        carroCompras = CarroCompras(carroCompras.items.toMutableMap())
        actualizarConteo()
    }

    fun limpiarCarrito() {
        carroCompras = CarroCompras()
        actualizarConteo()
    }

    private fun actualizarConteo() {
        totalItems = carroCompras.obtenerCantidadTotal()
    }

    fun obtenerSubtotal(): Int {
        return carroCompras.obtenerTotalPrecio()
    }
}