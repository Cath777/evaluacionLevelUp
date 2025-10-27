package com.example.levelupgamerapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.levelupgamerapp.viewmodel.ProductoViewModel

@Composable
fun AppContent(viewModel: ProductoViewModel) {
    val carro by viewModel.carroCompras.observeAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Productos")
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.productos) { producto ->
                Column {
                    Text(text = producto.nombre)
                    Text(text = "$${producto.precio}")
                    Button(onClick = { viewModel.agregarAlCarro(producto) }) {
                        Text(text = "Agregar al carro")
                    }
                }
            }
        }
        Text(text = "Items en el carro: ${carro?.items?.size ?: 0}")
    }
}