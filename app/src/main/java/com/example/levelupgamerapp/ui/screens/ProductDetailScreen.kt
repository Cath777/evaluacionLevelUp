package com.example.levelupgamerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.levelupgamerapp.R
import com.example.levelupgamerapp.data.local.AppDatabase
import com.example.levelupgamerapp.data.repository.ProductoRepository
import com.example.levelupgamerapp.data.repository.ReviewRepository
import com.example.levelupgamerapp.ui.components.ReviewInput
import com.example.levelupgamerapp.ui.components.ReviewItem
import com.example.levelupgamerapp.util.shareOnSocialMedia
import com.example.levelupgamerapp.viewmodel.ProductoViewModel
import com.example.levelupgamerapp.viewmodel.ProductoViewModelFactory
import com.example.levelupgamerapp.viewmodel.ReviewViewModel
import com.example.levelupgamerapp.viewmodel.ReviewViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(navController: NavController, productId: Int) {
    val context = LocalContext.current

    val productoRepository = remember { ProductoRepository() }
    val productoViewModel: ProductoViewModel = viewModel(factory = ProductoViewModelFactory(productoRepository))
    val product = productoViewModel.getProductoById(productId)

    val db = remember { Room.databaseBuilder(context, AppDatabase::class.java, "app-database").fallbackToDestructiveMigration().build() }
    val reviewDao = remember { db.reviewDao() }
    val reviewRepository = remember { ReviewRepository(reviewDao) }
    val reviewViewModel: ReviewViewModel = viewModel(factory = ReviewViewModelFactory(reviewRepository, productId))
    val reviews by reviewViewModel.reviews

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(product?.nombre ?: "Detalle del Producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver atrás")
                    }
                },
                actions = {
                    if (product != null) {
                        IconButton(onClick = {
                            shareOnSocialMedia(
                                context = context,
                                productName = product.nombre,
                                productUrl = "https://levelupgamer.com/producto/${product.id}" // URL de ejemplo
                            )
                        }) {
                            Icon(Icons.Default.Share, contentDescription = "Compartir")
                        }
                    }
                }
            )
        }
    ) {
        if (product == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Producto no encontrado")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = product.imagen ?: R.drawable.ic_launcher_background),
                    contentDescription = product.nombre,
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.nombre, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$${product.precio}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.descripcion, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { productoViewModel.agregarAlCarro(product) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Añadir al Carro")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Divider()
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Reseñas", style = MaterialTheme.typography.headlineSmall)
                
                ReviewInput { rating, comment ->
                    reviewViewModel.addReview(rating = rating, comment = comment, userId = 1)
                }

                if (reviews.isEmpty()) {
                    Text("Todavía no hay reseñas para este producto.")
                } else {
                    reviews.forEach { review ->
                        ReviewItem(review = review)
                    }
                }
            }
        }
    }
}
