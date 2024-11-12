package com.umgmi.cukio.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen() // Se llama a la función composable que define la UI
        }
    }

    @Composable
    fun MainScreen() {
        // Diseño de la pantalla principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Añadir padding general
            horizontalAlignment = Alignment.CenterHorizontally, // Centrado horizontal
            verticalArrangement = Arrangement.Center // Centrado vertical
        ) {

            // Título de la app
            Text(
                text = "Cukio", // Aquí puedes poner el nombre de tu aplicación
                fontSize = 34.sp, // Tamaño de texto
                fontWeight = FontWeight.Bold, // Estilo en negrita
                color = Color(0xFF086FC2), // Puedes cambiar el color a tu preferencia
                modifier = Modifier.padding(bottom = 20.dp) // Espaciado debajo del título
            )

            // Imagen (reemplaza 'avion' con la imagen de tu recurso)


            // Botón de Login


            // Botón de Register
            Button(
                onClick = {
                    // Navegación a la actividad de registro
                    val intent = Intent(this@MainActivity, RegistroActivity::class.java)
                    startActivity(intent)
                },
                modifier = Modifier
                    .width(200.dp)
                    .height(75.dp)
                    .padding(top = 15.dp), // Espaciado entre botones
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF086FC2) // Fondo morado
                )
            ) {

            }
        }
    }

    // Función de vista previa para Composable
    @Preview(showBackground = true)
    @Composable
    fun PreviewMainScreen() {
        MainScreen() // Muestra la vista previa de la pantalla principal
    }
}