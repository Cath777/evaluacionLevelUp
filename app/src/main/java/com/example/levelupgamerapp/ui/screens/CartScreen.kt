package com.example.levelupgamerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.levelupgamerapp.viewmodel.CarroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navController: NavController, carroViewModel: CarroViewModel) {

    val cartItems = carroViewModel.carroCompras.items.toList()
    val subtotal = carroViewModel.obtenerSubtotal()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mi Carrito") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (cartItems.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tu carrito está vacío")
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cartItems) { (producto, cantidad) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${producto.nombre} x$cantidad")
                            Row {
                                Button(onClick = { carroViewModel.agregarProducto(producto) }) {
                                    Text("+")
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Button(onClick = { carroViewModel.removerProducto(producto) }) {
                                    Text("-")
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Button(onClick = { carroViewModel.removerItemPorCompleto(producto) }) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Subtotal: $$subtotal",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* TODO: Lógica de pago */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Proceder al Pago")
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { carroViewModel.limpiarCarrito() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Vaciar Carrito")
                }
            }
        }
    }
}