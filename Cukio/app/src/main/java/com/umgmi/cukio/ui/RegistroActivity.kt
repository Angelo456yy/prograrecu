package com.umgmi.cukio.ui


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.umgmi.cukio.viewmodel.RegistroViewModel

class RegistroActivity : ComponentActivity() {

    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroScreen(viewModel, onRegister = { iniciarRegistro() })
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, Menu_principal::class.java)
        startActivity(intent)
        finish()
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun iniciarRegistro() {
        viewModel.registrarUsuario(
            onSuccess = { navigateToMainActivity() },
            onError = { showError(it) }
        )
    }
}

@Composable
fun RegistroScreen(viewModel: RegistroViewModel, onRegister: () -> Unit) {
    val nombre = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }
    val contraseña = remember { mutableStateOf("") }
    val usuarioTipo = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = nombre.value,
            onValueChange = {
                nombre.value = it
                viewModel.nombre = it
            },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = correo.value,
            onValueChange = {
                correo.value = it
                viewModel.correo = it
            },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = contraseña.value,
            onValueChange = {
                contraseña.value = it
                viewModel.contraseña = it
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            RadioButton(
                selected = usuarioTipo.value == "Transportista",
                onClick = {
                    usuarioTipo.value = "Transportista"
                    viewModel.usuarioTipo = "Transportista"
                }
            )
            Text("Transportista")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = usuarioTipo.value == "Empresa",
                onClick = {
                    usuarioTipo.value = "Empresa"
                    viewModel.usuarioTipo = "Empresa"
                }
            )
            Text("Empresa")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRegister() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
