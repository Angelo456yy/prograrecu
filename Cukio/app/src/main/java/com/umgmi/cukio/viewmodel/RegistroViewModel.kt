package com.umgmi.cukio.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.umgmi.cukio.model.Usuario
import android.util.Base64

class RegistroViewModel : ViewModel() {

    var nombre: String = ""
    var correo: String = ""
    var contraseña: String = ""
    var usuarioTipo: String = ""

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private fun cifrarContraseña(contraseña: String): String {
        return Base64.encodeToString(contraseña.toByteArray(), Base64.DEFAULT)
    }

    fun registrarUsuario(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || usuarioTipo.isEmpty()) {
            onError("Todos los campos son obligatorios")
            return
        }

        auth.createUserWithEmailAndPassword(correo, contraseña)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val contraseñaCifrada = cifrarContraseña(contraseña)
                    val usuario = Usuario(nombre, correo, usuarioTipo)
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    firestore.collection("usuarios").document(userId)
                        .set(usuario)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { e -> onError("Error al guardar los datos: ${e.message}") }
                } else {
                    onError("Error al registrar usuario: ${task.exception?.message}")
                }
            }
    }
}
