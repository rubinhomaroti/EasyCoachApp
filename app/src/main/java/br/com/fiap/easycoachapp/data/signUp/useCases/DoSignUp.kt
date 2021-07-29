package br.com.fiap.easycoachapp.data.signUp.useCases

import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.helpers.IdGenerator
import br.com.fiap.easycoachapp.domain.usecases.signUp.DoSignUpContract
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

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

    private fun registerMock() : Task<DocumentReference> {
        val se1 = SessionEntity(
            uid = IdGenerator().generate(),
            title = "Rapport",
            description = "Deep diving nos três melhores e piores momentos da vida do Coachee",
            scheduledDateTime = GregorianCalendar(2021, 7, 27).time,
            specialtyUid = "xx36DL08o0Mk5BCNLp7p",
            hasCancellationFee = true,
            inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
            sessionNumber = 1
        )

        val se2 = SessionEntity(
            uid = IdGenerator().generate(),
            title = "Linguagem do amor",
            description = "Entender qual é a linguagem do amor em seus relacionamentos",
            scheduledDateTime = GregorianCalendar(2021, 7, 28).time,
            specialtyUid = "xx36DL08o0Mk5BCNLp7p",
            hasCancellationFee = true,
            inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
            sessionNumber = 2
        )

        val se3 = SessionEntity(
            uid = IdGenerator().generate(),
            title = "Crenças",
            description = "Entender as crenças limitantes em seus relacionamentos",
            scheduledDateTime = GregorianCalendar(2021, 7, 29).time,
            specialtyUid = "xx36DL08o0Mk5BCNLp7p",
            hasCancellationFee = true,
            inviteUrl = "https://meet.google.com/xcv-jrwc-ira",
            sessionNumber = 3
        )

        val sessions = ArrayList<SessionEntity>()
        sessions.add(se1)
        sessions.add(se2)
        sessions.add(se3)

        val coachee = CoacheeEntity(
            uid = "e10967e9-4e3d-4cb6-8e77-3c94f933db77",
            email = "mauricio@fiap.com.br",
            password = null,
            name = "Mauricio",
            birthDate = GregorianCalendar(1985, 5, 22).time,
            sex = "M",
            cpf = "88806554000",
            contactNumber = "11988443322",
            sessions = sessions
        )
        val coachees = ArrayList<CoacheeEntity>()
        coachees.add(coachee)

        val sp1 = SpecialtyEntity(
            uid = "xx36DL08o0Mk5BCNLp7p",
            description = "Amor"
        )

        val sp2 = SpecialtyEntity(
            uid = "Nl2XCUdAXRZIAGsqHjAh",
            description = "Financeiro"
        )
        val specialties = ArrayList<SpecialtyEntity>()
        specialties.add(sp1)
        specialties.add(sp2)

        val coach = CoachEntity(
            uid = "btVMZvxo2NgX2TVGSjtuJiU20rc2",
            email = "teste@fiap.com.br",
            password = null,
            name = "FIAP ON",
            birthDate = GregorianCalendar(1997, 1, 14).time,
            sex = "M",
            cpf = "12345678910",
            contactNumber = "11991918181",
            cancellationFee = 0.1,
            cnpj = "42067115000143",
            coachees = coachees,
            specialties = specialties
        )

        return db.collection("coachs").add(coach)
    }
}