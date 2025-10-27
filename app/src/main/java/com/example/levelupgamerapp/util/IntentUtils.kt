package com.example.levelupgamerapp.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/**
 * Abre una conversación de WhatsApp con un número y mensaje predefinidos.
 */
fun openWhatsAppSupport(context: Context, phoneNumber: String, prefilled: String) {
    val uri = Uri.parse("https://wa.me/$phoneNumber?text=${Uri.encode(prefilled)}")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "WhatsApp no está instalado.", Toast.LENGTH_SHORT).show()
    }
}
fun openGoogleMaps(context: Context, lat: Double, lng: Double, title: String) {
    val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng(${Uri.encode(title)})")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {

        val genericMapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$lng?q=$lat,$lng"))
        if (genericMapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(genericMapIntent)
        } else {
            Toast.makeText(context, "No se encontró ninguna aplicación de mapas.", Toast.LENGTH_SHORT).show()
        }
    }
}

fun shareOnSocialMedia(context: Context, productUrl: String, productName: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "¡Mira este producto en LevelUp!: $productName\n$productUrl")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Compartir '$productName' en...")
    context.startActivity(shareIntent)
}
