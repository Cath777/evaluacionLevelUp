package com.example.levelupgamerapp.data.repository

import com.example.levelupgamerapp.data.local.entity.UsuarioEntity

fun aplicarDescuento(subtotal: Double, usuario: UsuarioEntity?): Double {
    var total = subtotal
    if (usuario?.descuentoduoc == true) {
        total = total * 0.80 // <- es un 20% de descuento
    }
    return total
}
