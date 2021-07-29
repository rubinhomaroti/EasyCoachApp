package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.*
import br.com.fiap.easycoachapp.domain.helpers.DomainError
import br.com.fiap.easycoachapp.domain.usecases.session.CreateSessionContract
import com.google.firebase.firestore.FirebaseFirestore

class CreateSession(
    private val db: FirebaseFirestore
) : CreateSessionContract {
    override fun execute(
        fromCoach: CoachEntity,
        toCoachee: CoacheeEntity,
        sessionInvitation: SessionEntity,
        onSuccessListener: () -> Unit,
        onFailureListener: (DomainError) -> Unit
    ) {
        db.collection("coachs")
            .whereEqualTo("uid", fromCoach.uid)
            .get()
            .addOnSuccessListener { coachDocuments ->
                val coach = coachDocuments.firstOrNull()
                if (coach != null) {
                    val coachEntity = CoachEntity.fromJson(coach.data)
                    val coachee = coachEntity.coachees?.firstOrNull { c -> c.uid == toCoachee.uid }
                    if (coachee != null) {
                        coachee.sessions?.add(sessionInvitation)
                        coach.reference.set(coachEntity)
                            .addOnSuccessListener { onSuccessListener() }
                            .addOnFailureListener { onFailureListener(DomainError.FIRESTORE_ERROR) }
                    }
                }
            }
            .addOnFailureListener { onFailureListener(DomainError.FIRESTORE_ERROR) }
    }
}