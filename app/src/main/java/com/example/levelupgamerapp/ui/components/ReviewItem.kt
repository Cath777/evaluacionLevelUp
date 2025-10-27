package com.example.levelupgamerapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.levelupgamerapp.data.local.entity.ReviewEntity

@Composable
fun ReviewItem(review: ReviewEntity) {
    Card(modifier = Modifier.padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                repeat(review.rating) {
                    Icon(Icons.Default.Star, contentDescription = "Star", tint = Color(0xFFFFD700)) // Color dorado
                }
                repeat(5 - review.rating) {
                    Icon(Icons.Default.Star, contentDescription = "Star", tint = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            if (!review.comment.isNullOrBlank()) {
                Text(text = review.comment, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(text = "- Usuario ${review.userId} el ${review.createdAt}", style = MaterialTheme.typography.bodySmall, fontStyle = FontStyle.Italic)
        }
    }
}
