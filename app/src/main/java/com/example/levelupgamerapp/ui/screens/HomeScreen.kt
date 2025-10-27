package com.example.levelupgamerapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.levelupgamerapp.data.repository.ProductoRepository
import com.example.levelupgamerapp.model.Producto
import com.example.levelupgamerapp.viewmodel.CarroViewModel
import com.example.levelupgamerapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, carroViewModel: CarroViewModel) {

    val context = LocalContext.current
    val productoRepository = ProductoRepository()
    val categorias = productoRepository.getCategorias()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LevelUp Store") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Cart.route) }) {
                        if (carroViewModel.totalItems > 0) {
                            BadgedBox(
                                badge = { Badge { Text(carroViewModel.totalItems.toString()) } }
                            ) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                            }
                        } else {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                        }
                    }
                    IconButton(onClick = {
                        navController.navigate(Screen.Profile.route)
                    }) {
                        Icon(Icons.Default.Person, contentDescription = "Perfil")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            categorias.forEach { categoria ->
                val productos = productoRepository.getProductosPorCategoria(categoria)
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = categoria,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(productos) { producto ->
                                ProductoCard(
                                    producto = producto,
                                    navController= navController,
                                    onAgregarClick = {
                                        carroViewModel.agregarProducto(producto)
                                        Toast.makeText(
                                            context,
                                            "'${producto.nombre}' añadido al carro",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ProductoCard(producto: Producto,navController: NavController, onAgregarClick: () -> Unit,) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(260.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = producto.imagen),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Text(producto.nombre, fontWeight = FontWeight.Bold)
            Text("$${producto.precio}")
            Button(onClick = onAgregarClick, modifier = Modifier.fillMaxWidth()) {
                Text("Agregar")
            }
            Button(
                onClick = { navController.navigate(Screen.ProductDetail.createRoute(producto.id)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ver Detalle")
            }
        }
    }
}