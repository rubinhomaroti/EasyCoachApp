package br.com.fiap.easycoachapp.data.login.useCases

import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.login.DoLoginContract
import com.google.firebase.auth.FirebaseAuth

class DoLogin(
    private val auth: FirebaseAuth
) : DoLoginContract {
    override fun execute(
        email: String,
        password: String,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        if (email.isNotBlank() && password.isNotBlank()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onSuccessListener()
                }
                .addOnFailureListener {
                    onFailureListener(DomainError.AUTH_ERROR)
                }
        } else {
            onFailureListener(DomainError.AUTH_ERROR)
        }
    }
}