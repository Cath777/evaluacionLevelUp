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
import com.example.levelupgamerapp.data.repository.AutentificacionRepository
import com.example.levelupgamerapp.data.util.Resultado
import com.example.levelupgamerapp.ui.navigation.Screen
import com.example.levelupgamerapp.viewmodel.LoginViewModel
import com.example.levelupgamerapp.viewmodel.LoginViewModelFactory

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = remember { Room.databaseBuilder(context, AppDatabase::class.java, "app-database").fallbackToDestructiveMigration().build() }
    val usuarioDao = remember { db.usuarioDao() }
    val authRepository = remember { AutentificacionRepository(usuarioDao) }
    val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(authRepository))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Iniciar Sesión", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.login(email, password) { resultado ->
                when (resultado) {
                    is Resultado.Logrado -> {
                        // Navega a la pantalla de Home y limpia el stack para que no pueda volver atrás
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                    is Resultado.Error -> {
                        Toast.makeText(context, resultado.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.navigate(Screen.Register.route) }) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}
