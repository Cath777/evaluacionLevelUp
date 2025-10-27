package com.example.levelupgamerapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.levelupgamerapp.R
import com.example.levelupgamerapp.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    product: Producto,
    onAddToCart: (Producto) -> Unit,
    onItemClick: (Producto) -> Unit
) {
    Card(
        onClick = { onItemClick(product) },
        modifier = Modifier
            .width(200.dp) // Ancho
            .padding(horizontal = 8.dp) // Espacio entre las cards
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imagen ?: R.drawable.ic_launcher_background),
                contentDescription = product.nombre,
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$${product.precio}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { onAddToCart(product) }) {
                Text("Añadir al Carro")
            }
        }
    }
}
