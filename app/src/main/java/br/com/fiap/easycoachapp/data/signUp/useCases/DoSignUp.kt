package br.com.fiap.easycoachapp.data.signUp.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.CoacheeEntity
import br.com.fiap.easycoachapp.domain.entities.UserEntity
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.helpers.UserType
import br.com.fiap.easycoachapp.domain.helpers.UserType.*
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
        type: UserType,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        if (user.email.isNotBlank() && user.password.isNotBlank()) {
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnSuccessListener {
                    val currentUser = auth.currentUser
                    val userProfileChangeRequest =
                        UserProfileChangeRequest.Builder().setDisplayName(user.name).build()
                    currentUser!!.updateProfile(userProfileChangeRequest)
                        .addOnCompleteListener {
                            registerUserDetails(user, type).addOnCompleteListener {
                                onSuccessListener()
                            }
                        }
                }
                .addOnFailureListener {
                    onFailureListener(DomainError.SIGN_UP_ERROR)
                }
        } else {
            onFailureListener(DomainError.SIGN_UP_ERROR)
        }
    }

    private fun registerUserDetails(user: UserEntity, type: UserType): Task<DocumentReference> {
        val currentUser = auth.currentUser
        when (type) {
            COACH -> {
                val coach = CoachEntity(
                    uid = currentUser!!.uid,
                    email = user.email,
                    password = user.password,
                    name = user.name,
                    birthDate = user.birthDate,
                    sex = user.sex,
                    cpf = user.cpf,
                    contactNumber = user.contactNumber,
                    cancellationFee = 0.10F,
                    cnpj = null,
                    coachees = null,
                    sessionPackages = null,
                    sessions = null,
                    specialties = null
                )
                return db.collection("coachs").add(coach)
            }
            COACHEE -> {
                val coachee = CoacheeEntity(
                    uid = currentUser!!.uid,
                    email = user.email,
                    password = user.password,
                    name = user.name,
                    birthDate = user.birthDate,
                    sex = user.sex,
                    cpf = user.cpf,
                    contactNumber = user.contactNumber,
                    sessions = null,
                    coachs = null
                )
                return db.collection("coachees").add(coachee)
            }
        }
    }
}