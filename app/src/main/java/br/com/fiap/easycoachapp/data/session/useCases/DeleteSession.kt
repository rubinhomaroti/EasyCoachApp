package br.com.fiap.easycoachapp.data.session.useCases

import br.com.fiap.easycoachapp.domain.entities.CoachEntity
import br.com.fiap.easycoachapp.domain.entities.SessionEntity
import br.com.fiap.easycoachapp.domain.usecases.session.DeleteSessionContract
import com.google.firebase.firestore.FirebaseFirestore

class DeleteSession (
    private val db: FirebaseFirestore
) : DeleteSessionContract {
    override fun execute(
        session: SessionEntity
    ) {
        db.collection("coachs")
            .whereEqualTo("uid", session.coachee?.coach?.uid)
            .get()
            .addOnSuccessListener { coachDocuments ->
                val coach = coachDocuments.firstOrNull()
                if (coach != null) {
                    val coachEntity = CoachEntity.fromJson(coach.data)
                    val coachee = coachEntity.coachees?.firstOrNull { c -> c.uid == session.coachee?.uid }
                    val sessionEntity = coachee?.sessions?.firstOrNull{c -> c.uid == session.uid}
                    if (coachee != null) {
                        coachee.sessions?.remove(sessionEntity)
                        coach.reference.set(coachEntity)
                    }
                }
            }
    }
}