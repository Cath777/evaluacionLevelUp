package com.example.levelupgamerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.levelupgamerapp.data.repository.ProductoRepository
import com.example.levelupgamerapp.model.CarroCompras
import com.example.levelupgamerapp.model.Producto

class ProductoViewModel(private val repository: ProductoRepository) : ViewModel() {
    val productos: List<Producto> = repository.getAllproductos()
    private val _carroCompras = MutableLiveData(CarroCompras())
    val carroCompras: LiveData<CarroCompras>
        get() = _carroCompras

    fun getProductoById(id: Int): Producto? {
        return repository.getProductoById(id)
    }

    fun agregarAlCarro(producto: Producto, cantidad: Int = 1) {
        _carroCompras.value?.agregarProducto(producto, cantidad)
        _carroCompras.value = _carroCompras.value
    }

    fun disminuirDelCarro(producto: Producto, cantidad: Int = 1) {
        _carroCompras.value?.removerProducto(producto, cantidad)
        _carroCompras.value = _carroCompras.value
    }

    fun removerItemDelCarro(producto: Producto) {
        _carroCompras.value?.removerItemPorCompleto(producto)
        _carroCompras.value = _carroCompras.value
    }

    fun limpiarCarro() {
        _carroCompras.value = CarroCompras()
    }
}