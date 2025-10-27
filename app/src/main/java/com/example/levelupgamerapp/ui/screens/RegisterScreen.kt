package com.example.levelupgamerapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.levelupgamerapp.data.local.AppDatabase
import com.example.levelupgamerapp.viewmodel.RegistroViewModelFactory
import com.example.levelupgamerapp.viewmodel.registroViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") } // Formato "YYYY-MM-DD"
    var referralCode by remember { mutableStateOf("") }

    val context = LocalContext.current
    // Instanciamos la BD y el DAO aquí. En una app grande, esto se haría con inyección de dependencias (Hilt).
    val db = remember { Room.databaseBuilder(context, AppDatabase::class.java, "app-database").fallbackToDestructiveMigration().build() }
    val usuarioDao = remember { db.usuarioDao() }
    val viewModel: registroViewModel = viewModel(factory = RegistroViewModelFactory(usuarioDao))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Crear Cuenta", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Nombre de Usuario") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo Electrónico") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = birthDate, onValueChange = { birthDate = it }, label = { Text("Fecha de Nacimiento (YYYY-MM-DD)") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = referralCode, onValueChange = { referralCode = it }, label = { Text("Código de Referido (Opcional)") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.registro(username, email, password, birthDate, referralCode) { success, message ->
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                if (success) {
                    navController.popBackStack()
                }
            }
        }) {
            Text("Registrarse")
        }
    }
}
