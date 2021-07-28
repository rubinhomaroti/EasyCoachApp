package br.com.fiap.easycoachapp.data.signUp.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.signUp.DoSignUpContract
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DoSignUp(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : DoSignUpContract {
    override fun execute(
        user: UserEntity,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        if (user.email.isNotBlank() && user.password != null && user.password.isNotBlank()) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser
                    val userProfileChangeRequest =
                        UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener {
                            registerUserDetails(user).addOnCompleteListener {
                                onSuccessListener()
                            }
                        }
                        .addOnFailureListener {
                            onFailureListener(DomainError.SIGN_UP_ERROR)
                        }
                }
                .addOnFailureListener {
                    onFailureListener(DomainError.SIGN_UP_ERROR)
                }
        } else {
            onFailureListener(DomainError.SIGN_UP_ERROR)
        }
    }

    private fun registerUserDetails(user: UserEntity): Task<DocumentReference> {
        val currentUser = auth.currentUser
        val currentCoach = user as CoachEntity
        val coach = CoachEntity(
            uid = currentUser!!.uid,
            email = currentCoach.email,
            password = null,
            name = currentCoach.name,
            birthDate = currentCoach.birthDate,
            sex = currentCoach.sex,
            cpf = currentCoach.cpf,
            contactNumber = currentCoach.contactNumber,
            cancellationFee = currentCoach.cancellationFee,
            cnpj = currentCoach.cnpj,
            coachees = currentCoach.coachees,
            specialties = currentCoach.specialties
        )
        return db.collection("coachs").add(coach)
    }
}