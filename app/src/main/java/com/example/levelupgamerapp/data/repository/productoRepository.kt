package com.example.levelupgamerapp.data.repository

import com.example.levelupgamerapp.R
import com.example.levelupgamerapp.model.Producto

class ProductoRepository {

    private val productos = listOf(
        // Juegos de Mesa
        Producto(1, "Catan", 24990, "Juegos de Mesa", "Juego de estrategia y gestión de recursos.", R.drawable.catan),
        Producto(2, "Dune: Imperium", 49990, "Juegos de Mesa", "Combina construcción de mazos y colocación de trabajadores.", R.drawable.dune),

        // Consolas
        Producto(3, "PlayStation 5", 549990, "Consolas", "Consola de última generación con SSD ultrarrápido.", R.drawable.ps6),
        Producto(4, "Xbox Series X", 529990, "Consolas", "La Xbox más potente de la historia.", R.drawable.xbox),

        // Sillas Gamers
        Producto(5, "Silla Gamer Pro", 149990, "Sillas Gamers", "Silla ergonómica para largas sesiones de juego.", R.drawable.sillagamer),
        Producto(6, "Silla de Escritorio ", 199990, "Sillas Gamers", "Diseño premium y máximo confort.", R.drawable.sillaescritorio),

        // Mouse
        Producto(7, "Mouse Gamer RGB", 39990, "Mouse", "Mouse óptico con 16,000 DPI y luces RGB.", R.drawable.mouse),
        
        // Poleras
        Producto(8, "Polera Zelda", 19990, "Poleras", "Polera de algodón con diseño de The Legend of Zelda.", R.drawable.zelda)
    )

    fun getAllproductos(): List<Producto> = productos
    fun getProductoById(id: Int): Producto? = productos.find { it.id == id }
    fun getCategorias(): List<String> = productos.map { it.categoria }.distinct()
    fun getProductosPorCategoria(categoria: String): List<Producto> = productos.filter { it.categoria == categoria }
}
