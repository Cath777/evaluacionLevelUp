package com.example.levelupgamerapp.data.util

sealed class Resultado<out T>{
    data class Logrado<out T>(val data:T): Resultado<T>()
    data class Error(val message: String): Resultado<Nothing>()
}