package br.com.fiap.easycoachapp.data.signUp.useCases

import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.helpers.UserType
import br.com.fiap.easycoachapp.domain.usecases.signUp.DoSignUpContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class DoSignUp (
    private val auth: FirebaseAuth
) : DoSignUpContract {
    override fun execute(
        user: UserEntity,
        type: UserType,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        if (user.email.isNotBlank() && user.password.isNotBlank()) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser
                    val userProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener{
                            onSuccessListener()
                        }
                }
                .addOnFailureListener{
                    onFailureListener(DomainError.SIGN_UP_ERROR)
                }
        } else {
            onFailureListener(DomainError.SIGN_UP_ERROR)
        }
    }
}